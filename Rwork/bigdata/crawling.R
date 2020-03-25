install.packages("mongolite")
library("mongolite")
library("stringr")

url <- "https://www.clien.net/service/board/park"

url_data <- readLines(url,encoding = "UTF-8")
#url_data 이거함부로했다가 R멈춤...

#정보확인======================================
#class(url_data)
#length(url_data)
#head(url_data)
#tail(url_data)

url_data[200]
#조건에 만족하는 데이터를 필터링
#문자열에 패턴을 적용해서 일치 여부를 T/F로 리턴
#1. str_detect(패턴을 검사할 문자열, 패턴)를 이용해서 웹페이지 전체에서 필요한 데이터만 먼저 추출
#2. 추출한 데이터 전체에서 내가 필요한 문자열만 추출
#str_extract -> 패턴에 일치하는 문자열을 리턴
#후방, 전방 탐색 정규 표현식

####데이터 필터링 : title ####

filter_data <-url_data[str_detect(url_data, "subject_fixed")]
title <- gsub("\t\t\t\t\t\t<span class=\"subject_fixed\" data-role=\"list-title-text\" title=\"","",filter_data)
title <- gsub("</span>","",title)
title <- str_extract(title,"(?<=\\>).*")
title

#### 데이터 필터링 : hit ####
url_data
hit_data <- url_data[str_detect(url_data,"\t\t\t\t\t<span class=\"hit\">")]
hit_data
hit <- str_extract(hit_data,"(?<=\">).*(?=</span>)")
hit <- hit[3:32]

####데이터 필터링 : url
str_detect(url_data,"subject_fixed")

myurl <- url_data[(which(str_detect(url_data,"subject_fixed"))-2)]
myurl
myurl <- str_extract(myurl,"(?<=href=\").*(?=data-role)")
myurl
myurl <- str_extract(myurl,".*(?=\")")
myurl
myurl <- paste0("https://www.clien.net",myurl)
myurl

result <- data.frame(title,hit,myurl)
write.csv(result,file="result.csv",fileEncoding = "EUC-KR")
save(result,file ="crawling.RData")

con <- mongo(collection="crawl", db="bigdata", url="mongodb://127.0.0.1:27017")
