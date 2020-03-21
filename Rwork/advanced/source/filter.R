#### 개요####
# 주어진 데이터에서 원하는 데이터만 추출
# dplyr은 문자열에 대한 작업에 특화된 기능이 많은 패키지
# C++로 구현되어 있어 가장 빠르다.
# dplyr라이브러리는 실행할 때 코드 전체를 범위지정해서 실행
library("dplyr")
data("airquality")
df <- airquality
head(df2)
df2 <- df[df$Day==1,]
df2 <- df[df$Day!=1,]
df[df$Day==1 | df$Day==2,]
df2

df2_data2 <- df2[df$Day %in% 1:2,]
sum(df$Day %in% 1:2)
df2_data2
nrow(df2_data2)

#dplyr패키지에서 지원하는 필터 관련 함수
filter(df,Day==1 & Month==5)
filter(df,Day %in% c(1,3,5))

#### 교재의 예제####
exam <-read.csv("data/csv_exam.csv")
exam
exam %>% filter(class==1)
exam %>% filter(class==2)
exam %>% filter(class==3)
exam %>% filter(class==4)
exam %>% filter(class==5)

exam %>%
  filter(class==1) %>% 
  filter(math>=50)
#미쳤다....

#select
exam %>% select(-class)

#arrange
exam %>% arrange(id)
exam %>% arrange(desc(id))
exam %>% arrange(desc(math)) %>% arrange(class)

exam %>% mutate(total=math+english+science, mean = total/3, info = ifelse((science>=70),"통과","재시험")) %>% head

exam %>%
  group_by(class) %>% 
  summarise(math_sum = sum(math),
            math_mean = mean(math),
            math_median = median(math),
            math_count = n()
            )





test1 <- data.frame(id=c(1,2,3,4,5),
                    mid_jumsu=c(77,56,99,100,99))
test2 <- data.frame(id=c(1,2,3,4,5),
                    final_jumsu=c(69,45,84,93,90))
namedata <- data.frame(class=c(1,2,3,4,5),
                       teacher=c("kim","lee","park","jang","hong"), stringsAsFactors = F)


str(namedata)
exam_new <-left_join(exam,namedata,by="class")
exam_new

groupA <- data.frame(id=c(1,2,3,4,5),
                     mid_jumsu=c(77,56,99,100,99))
groupB <- data.frame(id=c(6,7,8,9,10),
                     mid_jumsu=c(51,65,23,98,32))

group_total <- bind_rows(groupA,groupB)
group_total


cor.test(test1$mid_jumsu,test2$final_jumsu)
xtabs(~test1$mid_jumsu+test2$final_jumsu)

out = lm(test1$mid_jumsu~test2$final_jumsu)
out
summary(out)
confint.default(out)
plot(test1$mid_jumsu~test2$final_jumsu)
abline(out,col="red")

xtabs(~math+science,data=exam)
out = lm(math~science,data=exam)
summary(out)
plot(math~science,data=exam)
abline(out,col="red")





