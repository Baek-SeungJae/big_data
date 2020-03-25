library("stringr")
library("dplyr")
library("rvest")
library("httr")

result <- data.frame()
for(i in c(0:9)){
url <- "http://www.encar.com/dc/dc_carsearchlist.do?carType=kor#!%7B%22action%22%3A%22(And.Hidden.N._.(C.CarType.Y._.(C.Manufacturer.%ED%98%84%EB%8C%80._.(C.ModelGroup.%EA%B7%B8%EB%9E%9C%EC%A0%80._.Model.%EA%B7%B8%EB%9E%9C%EC%A0%80%20IG.))))%22%2C%22toggle%22%3A%7B%7D%2C%22layer%22%3A%22%22%2C%22sort%22%3A%22ModifiedDate%22%2C%22page%22%3A1%2C%22limit%22%3A20%7D"
#url <- paste(url,as.character(i))
url_data <- readLines(url,encoding = "EUC-KR")
start <- which(str_detect(url_data, "일반등록 차량목록-차량정보, 지역, 지역/등록일"))
end <- which(str_detect(url_data, "등록차량이 없습니다. 다른 조건으로 검색하세요."))
html <- read_html(url, encoding = "EUC-KR")
html
item <- html_node(x=html, xpath="/html/body/div[1]/div[2]/div[1]/div[2]/div[3]/div[1]/div[3]/div[3]/table")
item
tbl <- html_table(x=item, fill=FALSE)
tbl

html1 <- html_nodes(html, 'table.carlist')
html1
filter_data <- url_data[start:end]
filter_data
}