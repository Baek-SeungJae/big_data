####1####
midwest
df = data.frame(midwest) 
####2####
df = rename(df,total=poptotal)
df = rename(df,asian=popasian)
####3####
df[,"perasian"] <- 100*df$asian/df$total
####4####
totalasianavg = mean(df$perasian)
totalasianavg

####5####
df[,"exam"] <- ifelse(test=totalasianavg<df$perasian,yes="large",no="small")
qplot(df$exam)
