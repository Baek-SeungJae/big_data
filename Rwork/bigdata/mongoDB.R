library("stringr")
library("mongolite")

#mongodb에 저장하기 위해서는 크롤링 해야한다.
con <- mongo(collection="crawl", db="bigdata", url="mongodb://127.0.0.1:27017")

####mongodb에 저장하기####
if(con$count()>0){
  con$drop()
}
#mongodb에 데이터를 넣으려면 일단 dataframe형태로 만들어야 함
con$insert(data = result)


load(file="crawling.RData")

content_url <- as.character(result[,3])
content_url

url_data <- readLines(content_url[5] ,encoding = "UTF-8")
start = which(str_detect(url_data,"<article>"))
start
end = which(str_detect(url_data,"</article>"))
end
filterdata <- url_data[start:end]
filterdata <- paste(filterdata, collapse = "")
filterdata <- gsub("\t|&nbsp;","",filterdata)
filterdata <- gsub("<.*?>","",filterdata)
filterdata <- gsub("  ","",filterdata)
filterdata 
content_url[5]
#article = c(which(str_detect(url_data,"<article>")):which(str_detect(url_data,"</article>")))
#article
#data = paste(url_data[(which(str_detect(url_data,"<article>"))):(which(str_detect(url_data,"</article>")))])
#data
#data <- data[str_detect(data,"<p>")]
#data
#data <- str_extract(data,"(?<=<p>).*(?=</p>)")
#data
#data <- paste(data,collapse = "")
#data

