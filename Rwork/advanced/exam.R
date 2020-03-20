#ggplo2패키지에서 제공되는 mpg데이터를 분석
library("ggplot2")
exam = mpg
####step1. mpg를 dataframe으로 변경####
exam <- data.frame(exam)
class(exam)
####step2. mpg의 정보를 출력####
#행갯수, 열갯수, 위에서 10개 끝에서10개
nrow(exam)
ncol(exam)
head(exam,10)
tail(exam,10)
####step3. mpg의 컬럼명을 변경####
#cty -> city, hwy-> highway
install.packages("dplyr")
library("dplyr")
exam <- rename(exam, city=cty)
exam <- rename(exam, highway=hwy)
####step4. 파생변수 생성하기####
#total= cty와 hwy의 합
#avg = cty와 hwy의 평균
total = exam$city+exam$highway
avg = total/2
exam <- data.frame(exam,total,avg)
head(exam)
####step5.생성된 토탈을 가지고 요약정보 확인 ####
summary(exam)
####step6. info컬럼 추가 ####
#total값을 이용해서 평가 20이상이면 pass, 아니면 fail
exam[,"info"] <- ifelse(test=exam$total>=20, yes="pass", no="fail")
####step7. grade컬럼 추가 ####
#30이상 A, 25이상 B, 20이상 C, 나머지 D
test1=exam$total>=30
test2=exam$total>=25
test3=exam$total>=20
exam[,"grade"] <- ifelse(test=test1, yes="A", no=ifelse(test=test2,yes="B",no=ifelse(test=test3,yes="C",no="B")))
exam
