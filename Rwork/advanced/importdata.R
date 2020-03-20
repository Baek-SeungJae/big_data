install.packages("splitstackshape")
library("splitstackshape")

#readLines를 이용하면 데이터가 커도 어떤 구성인지 확인하기 편하다.
dfimport <- readLines("01_csv.csv" ,n=3)

#csv파일이므로, ,로 구분이 되어 있지만 사용자가 원하는 구분자를 적용해서 작성한 문자열인 경우
dfcsv <- data.frame(
                    mydata = dfimport,
                    stringsAsFactors = FALSE
                    )
strsplit(dfcsv$mydata, split=",")[[1]] # 리스트로 결과가 분리되어 불편하다.



dfcsv2 <- cSplit(indt=dfcsv, splitCols = "mydata", sep=",")
dfcsv2
class(dfcsv2)
#타입변경
#as의 함수들을 이용해서 변경, as는 ~~~가 아닌 것을 ~~~로 바꾼다.
dfcsv2 <- as.data.frame(dfcsv2)
class(dfcsv2)
class(dfcsv2$mydata_1)
class(dfcsv2$mydata_2)
class(dfcsv2$mydata_3)
class(dfcsv2$mydata_4)
class(dfcsv2$mydata_5)
str(dfcsv2)
#factor는 순서와 명목임, 
#1,2,3,.......값이 어떤 순서를 의미하는 경우 : 컬럼의 데이터가 순서형
#class컬럼 1,2,3,4....: 순서를 의미하지 않고 1반, 2반, 3반....
#순서가 적용되도록 적용된 factor타입을 char로 변경
for(i in 2:ncol(dfcsv2)){
  dfcsv2[,i] = as.character(dfcsv2[,i])
}

####tsv파일읽기####
#(\t)으로 구분된 파일
df3 <-read.delim("02_tsv.txt" , sep = "\t")
head(df3)

####xml파일읽기####
install.packages("XML")
library("XML")
df4 <- xmlTreeParse("03_xml.xml")
df4
#root elemnet와 하위엘리먼트만 추출
df4 <- xmlRoot(df4)
df4
#xml에 있는 모든 태그의 name과 value를 추출 
df4 <- xmlSApply(df4,function(x){
                                  xmlSApply(x, xmlValue)
                                })
df4 <- data.frame(t(df4),row.names = NULL)
df4

####xlsx파일읽기####
install.packages("readxl")
library("readxl")
df5 <- read_xlsx("07_xlsx.xlsx")
df5 # tiblle => data.frame과 비슷한 다른 종류의 라이브러리

