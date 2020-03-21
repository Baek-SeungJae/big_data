install.packages("stringr")
library("stringr")
#패턴
mytext <- "     test$uuuuu"
mytext2 <- "https://cran.r-project.org/"
#패턴과 일치하는 문자열도 리턴
str_extract(mytext2,".+(:)")
#패턴과 일치하는 문자열빼고 리턴
str_extract(mytext2,".+(?=:)") #전방탐색
str_extract(mytext2,"(?<=:).*") #후방탐색


#문자열 관련 함수
#paste - 벡터를 연결해서 하나의 문자열로 생성
#paste() - 여러 개를 연결
str <- c("java", "hadoop", "R", "mongodb")
paste(str,collapse=",")
paste0(mytext,mytext2)
data <- gsub("u","",mytext)
data 
str_trim(data)
