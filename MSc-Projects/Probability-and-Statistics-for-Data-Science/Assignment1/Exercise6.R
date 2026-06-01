# Exercise 6 – Student Grades & Satisfaction

# Data entry (30 observations)
Marketing <- c(
  99,89,32,44,45,25,47,86,97,94,55,61,94,55,
  66,60,83,67,46,52,29,54,25,90,33,77,79,97,100,25
)

Accounting <- c(
  89,92,37,51,74,57,30,55,49,45,68,47,87,34,
  36,59,94,42,28,39,61,56,49,80,29,55,46,93,92,64
)

Satisfaction <- c(
  1,1,0,1,0,0,1,1,0,1,1,1,1,1,
  1,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1
)

# Combine into data frame
df <- data.frame(Marketing, Accounting, Satisfaction)

# (a) 95% Confidence Interval for mean Marketing score

t.test(df$Marketing, conf.level = 0.95)

# (b) 90% Confidence Interval for mean Accounting score

t.test(df$Accounting, conf.level = 0.90)

# (c) Percentage satisfied + 95% CI for proportion

prop <- mean(df$Satisfaction == 1)   # sample percentage
prop

# 95% CI for a proportion (using prop.test)
prop.test(sum(df$Satisfaction == 1), length(df$Satisfaction), conf.level = 0.95)
