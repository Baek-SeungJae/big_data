df <- data.frame(mydata1=1:5, 
                 mydata2=letters[1:5], 
                 mydata3=c("java","android","@한%글^","이민호로로록","lv70")
                )
letters
####파일write####
write.csv(df,"endcoding_test.csv",row.names = FALSE)
write.csv(df,"endcoding_test_euc-kr.csv",row.names = FALSE , fileEncoding = "euc-kr")
write.csv(df,"endcoding_test_cp949.csv",row.names = FALSE , fileEncoding = "cp949")
write.csv(df,"endcoding_test_utf-8.csv",row.names = FALSE ,fileEncoding = "utf8")


####깨진파일처리하기####
read.csv("endcoding_test_utf-8.csv")
readLines("endcoding_test_utf-8.csv")
readLines("endcoding_test_utf-8.csv", encoding = "UTF-8")

#data.table - dataframe보다 강력
install.packages("data.table")
library("data.table")
dftable <- fread("endcoding_test_utf-8.csv")
head(dftable)
#인코딩함수
Encoding(dftable$mydata3) = "UTF-8"
head(dftable)

####파일write####
