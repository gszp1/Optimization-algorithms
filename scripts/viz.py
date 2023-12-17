#!/bin/python3
import argparse
import matplotlib.pyplot as plt
import numpy as np
from sympy import symbols, sympify
import imageio
import os
import io

parser = argparse.ArgumentParser(description="Generate gif visualization of algorithm.")
parser.add_argument("algorithm", type=str, help="Algorithm")
parser.add_argument("x_min", type=float, help="Minimum x value")
parser.add_argument("x_max", type=float, help="Maximum x value")
parser.add_argument("y_min", type=float, help="Minimum y value")
parser.add_argument("y_max", type=float, help="Maximum y value")
args, remaining_args = parser.parse_known_args()
func = ' '.join(remaining_args)

def get_main_function(expression, x_min, x_max, y_min, y_max):
    x, y = symbols('x y')
    func = sympify(expression)

    x_range = np.arange(x_min, x_max, 1)
    y_range = np.arange(y_min, y_max, 1)

    points = [(x, y) for x in x_range for y in y_range]
    xs, ys = np.array(points).T
    zs = np.array([func.subs({x: x_val, y: y_val}) for x_val, y_val in zip(xs, ys)])
    return xs, ys, zs    

def plot_main_function(ax):
    ax.set_xlim([x_min, x_max])
    ax.set_xlim([y_min, y_max])
    ax.plot3D(main_xs, main_ys, main_zs, 'green', alpha=0.5)
    
expression = func
x_min = args.x_min
x_max = args.x_max
y_min = args.y_min
y_max = args.y_max
main_xs, main_ys, main_zs = get_main_function(expression, x_min, x_max, y_min, y_max)

with open("data.txt") as f:
    data = f.readlines()
    
split_idx = [-1] + [idx for idx, value in enumerate(data) if value == '\n']
epochs = [data[start+1: end] for start, end in list(zip(split_idx, split_idx[1:]))]

def draw_epoch(epoch: list, output_file_name: str):
    points = []
    for chromosome in epoch:
        points.append([float(x) for x in chromosome.strip().split("|")])
    xs, ys, zs = np.matrix(points).T
    ax = plt.axes(projection='3d')
    ax.scatter3D(xs, ys, zs, cmap='Greens')
    plot_main_function(ax)
    plt.savefig(output_file_name)
    plt.clf()

os.makedirs("./out", exist_ok=True)
for idx, epoch in enumerate(epochs):
    if idx % 10 == 0:
        print(f"Drawing epoch {idx}")
    draw_epoch(epoch, f"./out/{idx}.png")

with imageio.get_writer(f'{args.algorithm}.gif', mode='I', duration=0.5) as writer:
    for idx, _ in enumerate(epochs):
        image = imageio.imread(f"./out/{idx}.png")
        writer.append_data(image)
writer.close()
