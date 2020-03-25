library("stringr")
library("dplyr")
library("rvest")
library("httr")
library("lubridate")
library("ggplot2")
library("psych")
library("PerformanceAnalytics")
library("MLmetrics")
library("caret")
#### 크롤링된 데이터 ####
url <- "https://www.bobaedream.co.kr/mycar/mycar_list.php?gubun=K&maker_no=49&group_no=5&page=5&order=S11&view_size=70"
readPage <- read_html(url,encoding="utf-8")
readPage %>% html_nodes("li.product-item") -> data
data  
readPage %>% html_nodes(".tit > a") %>% html_attr("title") -> title
readPage %>% html_nodes(".mode-cell.year > .text") %>% html_text() -> yy
yy

readPage %>% html_nodes(".mode-cell.fuel > .text") %>% html_text() -> fuel
fuel

readPage %>% html_nodes(".mode-cell.km > .text") %>% html_text() -> km
km

readPage %>% html_nodes(".mode-cell.price") -> price
price <- gsub("<.*?>|\r|\n|\t|&nbsp;","", price)
price <- gsub("  ","",price)
price <- price[-1]
price

crawled_data <- data.frame(title,yy,fuel,km,price, stringsAsFactors = F)

####데이터 수집 및 전처리####
prepro<-data.frame()
url <- "https://www.bobaedream.co.kr/mycar/mycar_list.php?gubun=K&maker_no=49&group_no=5&page=1&order=S11&view_size=70"
readPage <- read_html(url,encoding="utf-8")
readPage %>% html_nodes("li.product-item") -> data
data  
readPage %>% html_nodes(".tit > a") %>% html_attr("title") -> title
company <- str_remove(title," (?<=\\s).*")
model <- str_remove(title,".\\w(?>\\s)")
readPage %>% html_nodes(".mode-cell.year > .text") %>% html_text() -> yy
yy
yy <- paste("20",yy)
yy <- gsub("\\(.*?\\)|&nbsp;| ","", yy)
mm <- gsub(".*/","",yy)
yy <- gsub("/.*","",yy)
yy <- as.integer(yy)
mm <- as.integer(mm)
old <- ((year(now())-yy)*12 + (month(now())-mm))
old

readPage %>% html_nodes(".mode-cell.fuel > .text") %>% html_text() -> fuel
fuel

readPage %>% html_nodes(".mode-cell.km > .text") %>% html_text() -> km
km <- gsub("천","000",km)
km <- gsub("만","0000",km)
km <- gsub("km","",km)
km <- as.numeric(km)
km

readPage %>% html_nodes(".mode-cell.price") -> price
price <- gsub("<.*?>|\r|\n|\t|&nbsp;","", price)
price <- gsub("  ","",price)
price <- gsub("만원|,","",price)
price <- price[-1]
price

result <- data.frame(company,model,old,fuel,km,price, stringsAsFactors = F)
temp <- result[!(result$price == "팔림"),]
temp <- temp[!(str_detect(temp$price,"할부")),]
temp <- temp[!(str_detect(temp$price,"계약")),]
temp <- temp[!(str_detect(temp$price,"만료")),]
temp <- temp[!(str_detect(temp$price,"운용리스")),]
temp <- temp[!(str_detect(temp$price,"상담")),]
temp <- temp[!(temp$price == "보류"),]
temp
temp$price <- as.numeric(temp$price)

prepro <- bind_rows(prepro,temp)

#### 결과 확인 ####
#1.요약
summary(prepro)
boxplot(prepro$km)
boxplot(prepro$old)
boxplot(prepro$price)
table(prepro$fuel)


#### 이상치제거 ####

prepro$old <- ifelse(prepro$old < 0 | prepro$old > 1000, NA, prepro$old)
prepro$price <- ifelse(prepro$price < 0 | prepro$price > 9000, NA, prepro$price)
prepro <- na.omit(prepro)

#1. 이상치 제거 후 재확인
boxplot(prepro$km)
boxplot(prepro$old)
boxplot(prepro$price)
table(prepro$fuel)

#2.막대그래프-km분포
ggplot(data=prepro, aes(x = prepro$km))+geom_bar()

#3.correlation
cor(prepro[c(3,5,6)])
pairs(prepro[c(3,5,6)])

#correlation 확대
#plot(prepro$old, prepro$price)
#plot((prepro$km), prepro$price)

write.csv(prepro,"prepro.csv")
prepro <- read.csv("prepro.csv")

#### regression ####
#train, test table 분류
smp_size <- floor(0.7 * nrow(prepro))
set.seed(456)
train_ind <- sample(seq_len(nrow(prepro)), size = smp_size)
train1 <- prepro[train_ind, ]
test1 <- prepro[-train_ind, ]





####regression 모델 생성 , 테스트 - one hot encoding 전####
out1 = lm(price~old+km, data=train1)
out1
summary(out1)
confint.default(out1)
#ploat(out)은 return값이 많아서 필요할 때만 사용
#plot(out)

pairs(train1$price~train1$old+train1$km, lower.panel = NULL)
pairs.panels(train1,method = "pearson", density = TRUE, ellipses = TRUE, hist.col = "#00AFBB",scale=TRUE)

prediction1 <- round(predict(out1,test1,interval = "prediction"),0)
test1[,"prediction"] <- prediction1[,1]
test1[,"diff"] <- test1$price - test1$prediction
#prepro<-prepro[order(prepro$title),]
test1

#ggplot(data=test1[], aes(x=c(1:count(test1)), y=test1[]$diff))+ geom_point()

ggplot(data=test1[], aes(x=test1[]$price,y=test1[]$prediction))+geom_point(alpha = 1, aes(color=company))+geom_abline(slope = 1)+coord_cartesian(xlim = c(0,3000), ylim = c(0,3000))

R2_Score(test1[]$prediction,test1[]$price)
MAPE(test1[]$prediction,test1[]$price)
MAE(test1[]$prediction,test1[]$price)

#regression 모델 생성 , 테스트 - one hot encoding 후

#one hot encoing용 dummy생성
dmy <- dummyVars(formula = ~company, data = prepro)
dmy
prepro_new <- data.frame(prepro,predict(dmy, newdata = prepro))
prepro_new
#test, train table 분리
smp_size <- floor(0.7 * nrow(prepro_new))
set.seed(456)
train_ind <- sample(seq_len(nrow(prepro_new)), size = smp_size)
train2 <- prepro_new[train_ind, ]
test2 <- prepro_new[-train_ind, ]

# 굳이 c()로 한 이유가 있긴한데 설명하기가 너무 복잡함... 안하면 오류나여
out2 = lm(price~., data=train2[,c(4,6,7,8)]) 

out2
summary(out2)
confint.default(out2)
#ploat(out)은 return값이 많아서 필요할 때만 사용
#plot(out)

pairs(train2$price~train2$old+train2$km, lower.panel = NULL)
pairs.panels(train2,method = "pearson", density = TRUE, ellipses = TRUE, hist.col = "#00AFBB",scale=TRUE)
prediction2 <- round(predict(out2,test2,interval = "prediction"),0)
test2[,"prediction"] <- prediction2[,1]
test2[,"diff"] <- test2$price - test2$prediction
test2

#ggplot(data=test2[], aes(x=c(1:count(test2)), y=test2[]$diff))+ geom_point()
ggplot(data=test2[], aes(x=test2[]$price,y=test2[]$prediction))+geom_point(alpha = 1, aes(color=company))+geom_abline(slope = 1)+coord_cartesian(xlim = c(0,3000), ylim = c(0,3000))

R2_Score(test2[]$prediction,test2[]$price)
MAPE(test2[]$prediction,test2[]$price)
MAE(test2[]$prediction,test2[]$price)



#### 차종별 one hot encoding #####

dmy <- dummyVars(formula = ~model, data = prepro)
dmy
prepro_test <- data.frame(prepro,predict(dmy, newdata = prepro))
prepro_test
#test, train table 분리
smp_size <- floor(0.7 * nrow(prepro_test))
set.seed(456)
train_ind <- sample(seq_len(nrow(prepro_test)), size = smp_size)
train3 <- prepro_test[train_ind, ]
test3 <- prepro_test[-train_ind, ]
out3 = lm(price~., data=train3[,c(4,6,7:199)]) 
summary(out3)
confint.default(out3)
plot(out3)
prediction3 <- round(predict(out3,test3,interval = "prediction"),0)
test3[,"prediction"] <- prediction3[,1]
test3[,"diff"] <- test3$price - test3$prediction

test3 <- test3[,-c(8:199)]

ggplot(data=test3[], aes(x=test3[]$price,y=test3[]$prediction))+geom_point(alpha = 1, size=4, aes(color=company))+geom_abline(slope = 1)+coord_cartesian(xlim = c(0,3000), ylim = c(0,3000))

R2_Score(test3[]$prediction,test3[]$price)
MAPE(test3[]$prediction,test3[]$price)
MAE(test3[]$prediction,test3[]$price)
pairs(train3$price~train3$old+train3$km, lower.panel = NULL)
cor(prepro[c(7,4,6)])
pairs(prepro[c(7,4,6)])

#### 테스트 케이스 입력 ####
test_case = test3[1,]
test_case[1,8:199] <- 0
test_case[1,"company"] <- "기아"
test_case[1,"model"] <- "더 뉴 K5 2세대 2.0 가솔린 인텔리전트"
test_case[1,paste("model.",test_case$model,sep ="")] = 1
test_case[1,"old"] <- 23
test_case[1,"fuel"] <- "가솔린"
test_case[1,"km"] <- 10000
test_case[1,"price"] <- 1120
predict(out3,test_case,interval = "prediction")
