# Assignment 3: MLPs for Sentiment Classification and POS Tagging

## Overview
This assignment was completed for the Text Analytics subject during my MSc studies.

Although the original file is named Assignment 3, this is listed as Assignment 2 in this portfolio because the professor skipped Assignment 2 during the course.

The assignment focused on Natural Language Processing and Deep Learning. It implemented and evaluated Multi-Layer Perceptron models using PyTorch for two NLP tasks:
- Sentiment classification on movie reviews
- Part-of-Speech tagging on English sentences

## Project Purpose
The purpose of this assignment was to practise deep learning methods for text analytics and compare how different text representations and neural network architectures perform on NLP tasks.

## Technologies Used
- Python
- Jupyter Notebook
- PyTorch
- NLTK
- Scikit-learn
- Gensim
- GloVe embeddings
- Word2Vec
- Bag-of-Words
- TF-IDF
- Multi-Layer Perceptrons
- Deep learning for NLP

## Project Files
- `Text_Analytics_Assignment3_GR_FINAL.ipynb` — Jupyter Notebook containing the implementation
- `Text_Analytics_Assignment3_Report.pdf` — final assignment report
- `README.md` — project documentation

## Task 1: Sentiment Classification
The first task focused on sentiment classification using the NLTK `movie_reviews` dataset.

The dataset contained 2,000 movie reviews:
- 1,000 positive reviews
- 1,000 negative reviews

The assignment compared three text representations:
- Bag-of-Words
- TF-IDF
- Word2Vec document centroids

Several MLP architectures were tested:
- Linear model
- Plain MLP
- MLP with Dropout
- MLP with BatchNorm
- MLP with LayerNorm

The models were compared with:
- Majority baseline
- Logistic Regression baseline
- Temporal ensemble model

## Task 2: POS Tagging
The second task focused on Part-of-Speech tagging using the UD English-EWT dataset.

The model used a window-based approach:
- A context window around each token
- Pretrained GloVe-100 embeddings
- MLP-based classifiers
- 17 universal POS tags

The models were evaluated using:
- Precision
- Recall
- F1-score
- PR-AUC
- Confusion matrices

## Main Results
For sentiment classification, TF-IDF performed best overall. The best test result came from the TF-IDF Plain MLP, while the TF-IDF Dropout ensemble achieved the highest PR-AUC.

For POS tagging, all MLP models outperformed the most-frequent-tag baseline. The Linear model was selected based on development-set performance, while the LayerNorm model had the highest test macro-F1.

## What I Learned
- How to preprocess text for NLP tasks
- How to compare Bag-of-Words, TF-IDF, and Word2Vec representations
- How to build MLP models in PyTorch
- How to use dropout, batch normalization, and layer normalization
- How to evaluate NLP models using macro-F1 and PR-AUC
- How to perform POS tagging with word embeddings
- How to avoid test leakage by selecting models using the development set
- How deep learning models behave differently depending on feature representation

## Academic Context
This project was completed as part of my MSc studies in the Text Analytics subject.
