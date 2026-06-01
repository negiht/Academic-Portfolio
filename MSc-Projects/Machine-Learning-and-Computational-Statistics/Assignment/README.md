# Assignment 1: Hyperspectral Image Analysis

## Overview
This assignment was completed for the Machine Learning and Computational Statistics subject during my MSc studies.

The assignment focused on hyperspectral image analysis using the Salinas dataset. The work included spectral unmixing, reconstruction error comparison, and classification of hyperspectral pixels.

## Project Purpose
The purpose of this assignment was to apply machine learning and computational statistics methods to hyperspectral image data.

The project explored how spectral signatures can be used to estimate material abundance and classify pixels into different land-cover or material classes.

## Technologies Used
- Python
- Jupyter Notebook
- NumPy
- SciPy
- Matplotlib
- Scikit-learn
- MATLAB `.mat` files
- Machine learning
- Computational statistics

## Project Files
- `Assignment.ipynb` — Jupyter Notebook containing the implementation
- `Assignment.pdf` — exported assignment report with results and visualizations
- `classification_labels.mat` — training, test, and operational labels
- `Salinas_cube.mat` — hyperspectral image cube
- `Salinas_endmembers.mat` — endmember spectral signatures
- `Salinas_ground_truth.mat` — ground truth class labels
- `README.md` — project documentation

## Main Topics
- Hyperspectral image analysis
- Spectral unmixing
- Endmember spectral signatures
- Abundance map estimation
- Reconstruction error
- Least Squares
- Non-negative Least Squares
- Fully Constrained Least Squares
- LASSO regularization
- Pixel classification
- Confusion matrices
- Cross-validation
- Classification accuracy

## Part 1: Spectral Unmixing
The first part of the assignment focused on estimating abundance maps from hyperspectral data.

The following methods were compared:
- Least Squares
- Least Squares with sum-to-one constraint
- Non-negative Least Squares
- Fully Constrained Least Squares
- LASSO

The methods were evaluated using reconstruction error and visual comparison of abundance maps.

## Part 2: Classification
The second part focused on classifying hyperspectral pixels into classes.

The classification methods included:
- Naive Bayes
- Minimum Euclidean Distance
- k-Nearest Neighbors
- Bayesian Classifier / Linear Discriminant Analysis

The classifiers were evaluated using:
- 10-fold cross-validation
- Test set accuracy
- Normalized confusion matrices

## Part 3: Combination
The final part compared the results from spectral unmixing and classification.

Spectral unmixing provides fractional information about material composition, while classification assigns a single class label to each pixel. The assignment compared how both approaches can be useful for interpreting hyperspectral images.

## What I Learned
- How to work with hyperspectral image data
- How to load and process MATLAB `.mat` files in Python
- How spectral unmixing estimates material abundance
- How reconstruction error can be used to compare models
- How to apply classification algorithms to high-dimensional spectral data
- How to evaluate classifiers using cross-validation and confusion matrices
- How machine learning methods can be used for remote sensing data

## Academic Context
This project was completed as part of my MSc studies in the Machine Learning and Computational Statistics subject.
