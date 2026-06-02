# Assignment 1: Illumination Design with Equality-Constrained Least Squares

## Overview
This assignment was completed for the Numerical Optimization and Large Scale Linear Algebra subject during my MSc studies.

The assignment focused on an illumination design problem where a set of lamps is used to light a rectangular area as uniformly as possible.

The room was divided into a 25 × 25 grid, giving 625 pixels. The aim was to choose the powers of 10 lamps so that the resulting illumination was as close as possible to a desired uniform illumination level.

## Project Purpose
The purpose of this assignment was to apply numerical optimization and linear algebra methods to a practical design problem.

The project explored how least-squares methods and equality constraints can be used to optimize lamp powers and improve illumination uniformity.

## Technologies Used
- Python
- Jupyter Notebook
- NumPy
- Pandas
- Matplotlib
- Least squares optimization
- Equality-constrained optimization
- KKT systems
- Linear algebra

## Project Files
- `Assignment1.ipynb` — Jupyter Notebook containing the implementation, visualizations, results, and explanations
- `README.md` — project documentation

## Main Topics
- Illumination modelling
- Inverse-square law
- Matrix formulation
- Least squares
- Equality-constrained least squares
- KKT systems
- RMS error analysis
- Numerical optimization
- Random search
- Large-scale linear algebra

## Problem Description
The illumination model was written as:

```text
l = A p
