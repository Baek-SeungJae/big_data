exam <- read.csv("csv_exam.csv")
exam

#전체 레코드의 맨 위에서부터, 아래에서 부터 정의한 갯수만큼 가져오기
head(exam, n=5)
tail(exam, 5)

#실제 뷰어로 보기
View(exam)
#행의 갯수
nrow(exam)
#열의 갯수
ncol(exam)
#행과 열의 갯수
dim(exam)
#타입확인
class(exam)
#구조확인
str(exam)
#데이터에 대한 요약 정보
summary(exam)

#컬럼의 이름을 변경
install.packages("dplyr")
library("dplyr")
#rename(data, 변경할 컬럼명=기존컬럼명)
exam <- rename(exam, eng=english)
table(exam$eng)
qplot(exam$eng)
