# Exercise 5 

# Unique seed of my choice (use the same before EACH sample)
my_seed <- 123456

# 1. Generate the four samples

## Discrete: X ~ Poisson(3)
set.seed(my_seed)
X_100    <- rpois(100,   lambda = 3)

set.seed(my_seed)
X_10000  <- rpois(10000, lambda = 3)

## Continuous: Y ~ Exponential(1)
set.seed(my_seed)
Y_100    <- rexp(100,    rate = 1)

set.seed(my_seed)
Y_10000  <- rexp(10000,  rate = 1)

# Helper function to analyze a sample

analyze_sample <- function(x, dist_name, sample_name) {
  cat("\n========================================\n")
  cat("Distribution:", dist_name, "| Sample:", sample_name, "\n")
  cat("Sample size:", length(x), "\n")
  cat("========================================\n\n")
  
  ## (a) Graphical representation
  # Use histogram for continuous; barplot for discrete (integer-valued)
  if (all(x == floor(x))) {
    # treat as discrete
    counts <- table(x)
    barplot(counts,
            main = paste("Barplot of", dist_name, "-", sample_name),
            xlab = "Values",
            ylab = "Frequencies")
  } else {
    # treat as continuous
    hist(x,
         main = paste("Histogram of", dist_name, "-", sample_name),
         xlab = "Values",
         ylab = "Frequency")
  }
  
  ## (b) Descriptive statistics
  m    <- mean(x)
  s    <- sd(x)
  med  <- median(x)
  iqr  <- IQR(x)
  
  cat("(b) Descriptive statistics:\n")
  cat("    Mean              =", m, "\n")
  cat("    Standard deviation=", s, "\n")
  cat("    Median            =", med, "\n")
  cat("    IQR               =", iqr, "\n\n")
  
  ## (c) Proportion in [min(mean, median), max(mean, median)]
  lower <- min(m, med)
  upper <- max(m, med)
  prop_in_interval <- mean(x >= lower & x <= upper)
  
  cat("(c) Proportion of sample in [min(mean, median), max(mean, median)]:\n")
  cat("    Interval = [", lower, ", ", upper, "]\n", sep = "")
  cat("    Proportion =", prop_in_interval, "\n\n")
  
  ## (d) Lowest 1% quantile (sample and theoretical)
  q1_sample <- quantile(x, 0.01, type = 7)
  cat("(d) Lower 1% tail:\n")
  cat("    Empirical 1% quantile (sample) =", q1_sample, "\n")
  
  ## (e) Upper 1% quantile (sample and theoretical)
  q99_sample <- quantile(x, 0.99, type = 7)
  cat("\n(e) Upper 1% tail:\n")
  cat("    Empirical 99% quantile (sample) =", q99_sample, "\n\n")
  
  # Return list of useful values if you want to store them
  invisible(list(
    mean   = m,
    sd     = s,
    median = med,
    IQR    = iqr,
    lower_mean_median = lower,
    upper_mean_median = upper,
    proportion_in_interval = prop_in_interval,
    q1_sample  = q1_sample,
    q99_sample = q99_sample
  ))
}

# 2. Theoretical quantiles for X and Y (1% and 99%)

# X ~ Poisson(lambda = 3)
q1_X_theoretical  <- qpois(0.01, lambda = 3)
q99_X_theoretical <- qpois(0.99, lambda = 3)

cat("Theoretical quantiles for X ~ Poisson(3):\n")
cat("    1%  quantile =", q1_X_theoretical,  "\n")
cat("    99% quantile =", q99_X_theoretical, "\n\n")

# Y ~ Exponential(rate = 1)
q1_Y_theoretical  <- qexp(0.01, rate = 1)
q99_Y_theoretical <- qexp(0.99, rate = 1)

cat("Theoretical quantiles for Y ~ Exponential(1):\n")
cat("    1%  quantile =", q1_Y_theoretical,  "\n")
cat("    99% quantile =", q99_Y_theoretical, "\n\n")

# 3. Run the analysis for all four datasets

res_X_100    <- analyze_sample(X_100,   "Poisson(3)",      "n = 100")
res_X_10000  <- analyze_sample(X_10000, "Poisson(3)",      "n = 10000")
res_Y_100    <- analyze_sample(Y_100,   "Exponential(1)",  "n = 100")
res_Y_10000  <- analyze_sample(Y_10000, "Exponential(1)",  "n = 10000")
