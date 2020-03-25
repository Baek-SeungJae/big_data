library("mongolite")
library("stringr")
library("dplyr")

####모두의 광장 1~10페이지 모든 게시글 크롤링####
# 1. 모든 페이지의 title, hit, url, content 추출하기
# 2. crawl_result.csv, crawl_result.RData 저장
# 3. mongodb저장(300개)
# 4. for, if문 활용
result <- data.frame()
for(i in c(0:9)){
url <- "https://www.clien.net/service/group/community?&od=T31&po="
url <- paste(url,as.character(i))

url_data <- readLines(url,encoding = "UTF-8")

filter_data <-url_data[str_detect(url_data, "subject_fixed")]
title <- gsub("\t\t\t\t\t\t<span class=\"subject_fixed\" data-role=\"list-title-text\" title=\"","",filter_data)
title <- gsub("</span>","",title)
title <- str_extract(title,"(?<=\\>).*")
title
hit_data <- url_data[str_detect(url_data,"\t\t\t\t\t<span class=\"hit\">")]
hit_data
hit <- str_extract(hit_data,"(?<=\">).*(?=</span>)")
hit <- hit[2:31]
hit
myurl <- url_data[(which(str_detect(url_data,"class=\"list_subject\"")))]
myurl
myurl <- str_extract(myurl,"(?<=href=\").*(?=data-role)")
myurl
myurl <- na.omit(myurl)
myurl
myurl <- str_extract(myurl,".*(?=\")")
myurl
myurl <- paste0("https://www.clien.net",myurl)
myurl
content <-c ()
for(j in c(1:30)){
  temp <- readLines(myurl[j] , encoding = "UTF-8")
  start = which(str_detect(temp,"<article>"))
  end = which(str_detect(temp,"</article>"))
  temp <- temp[start:end]
  temp <- paste(temp, collapse = "")
  temp <- gsub("\t|&nbsp;","",temp)
  temp <- gsub("<.*?>","",temp)
  temp <- gsub("  ","",temp)
  content <- c(content,temp)
}
content
result <- bind_rows(result,data.frame(title,hit,myurl,content))
}
write.csv(result, file ="crwaling_result.csv")
save(result, file = "crwaling_result.RData")

con <- mongo(collection="crawl", db="bigdata", url="mongodb://127.0.0.1:27017")
if(con$count()>0){
  con$drop()
}
con$insert(data = result)
