score <- matrix(c(c(80,90,70,100),c(80,99,78,72),c(90,78,82,78),c(99,89,78,90)), ncol=4, nrow = 4)
score
colnames(score) <- c("국어","영어","과학","수학")
rownames(score) <- c("kim","lee","home","jang")
score

avg_name <- colMeans(score)
avg_name


avg_subject <- rowMeans(score)
avg_subject

