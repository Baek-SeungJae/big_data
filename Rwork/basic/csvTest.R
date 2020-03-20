m <- matrix(1:20,ncol =4)
m
d <-data.frame(m)
d
myresult <- d[d$X3 >= 13,]
myresult
write.csv(myresult,file="result.csv")

#csv_exam.csv를 읽어서 데이터를 수정한 후
#csv_exam_result.csv
# - sicence가 80이상인 데이터를 추출
# - 추출된 데이터에 mytotal과 myavg컬럼을 추가
# - mytotal : 모든 과목의 총점
# - myavg : 모든 과목의 평균
mdf <- read.csv("csv_exam.csv")
temp <- mdf[mdf$science>=80,]
#mytotal = rowSums(temp[3:5])
mytotal <-c(temp$math)+c(temp$english)+c(temp$science)
myavg <-round((c(temp$math)+c(temp$english)+c(temp$science))/3,2)
#myavg = round(rowMeans(temp[3:5]),2)
temp <- data.frame(temp,mytotal,myavg)
temp
