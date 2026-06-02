# Assignment 1: N-gram Language Models

## Overview
This assignment was completed for the Text Analytics subject during my MSc studies.

The assignment focused on implementing and evaluating n-gram language models using a real text corpus. The project included bigram and trigram models with different smoothing methods, as well as autocomplete generation.

## Project Purpose
The purpose of this assignment was to understand how classical statistical language models work and how smoothing methods affect model performance.

The project also explored how language models can be used for text generation and autocomplete.

## Technologies Used
- Python
- Jupyter Notebook
- NLTK
- Brown corpus
- N-gram language modelling
- Laplace smoothing
- Kneser–Ney smoothing
- Beam search
- Greedy decoding

## Project Files
- `Assignment1_Text_Analytics.ipynb` — Jupyter Notebook containing the implementation
- `Text_Analytics_Assignment1_Report.pdf` — final assignment report
- `README.md` — project documentation

## Dataset
The assignment used the **Brown corpus** from NLTK, specifically the `lore` category.

The dataset was split into:
- 80% training set
- 10% development set
- 10% test set

The preprocessing included:
- Lowercasing tokens
- Shuffling sentences with a fixed seed
- Creating a vocabulary from the training set only
- Replacing rare and unknown words with `*UNK*`
- Adding special start and end tokens

## Models Implemented
The project implemented:

### Bigram Language Models
A bigram model estimates the probability of each word using the previous word as context.

### Trigram Language Models
A trigram model estimates the probability of each word using the previous two words as context.

### Laplace Smoothing
Laplace smoothing was used to avoid zero probabilities by adding one to n-gram counts.

### Kneser–Ney Smoothing
Kneser–Ney smoothing was implemented as a stronger smoothing method that considers how many different contexts a word appears in.

## Evaluation
The models were evaluated using:
- Cross-entropy
- Perplexity

Lower perplexity indicates a better language model.

The results showed that Kneser–Ney smoothing performed much better than Laplace smoothing. The Laplace trigram model had the worst performance because trigram data is sparse and add-one smoothing distributes too much probability to unseen n-grams.

## Autocomplete
The trained models were also used for autocomplete.

Two decoding strategies were tested:
- Greedy decoding
- Beam search

The tuned Kneser–Ney trigram model produced the best autocomplete results, combining lower generated perplexity, sentence completion, and fewer repetitions.

## What I Learned
- How n-gram language models work
- How to build bigram and trigram models
- Why smoothing is necessary in language modelling
- How Laplace and Kneser–Ney smoothing differ
- How to evaluate language models using perplexity
- How greedy decoding and beam search work
- How classical NLP models can be used for autocomplete

## Academic Context
This project was completed as part of my MSc studies in the Text Analytics subject.
