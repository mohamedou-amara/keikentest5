# Use the official Node.js image as the base image
FROM node:16

# Create a directory for the app
RUN mkdir -p /Users/macamara/Desktop/java-2022-12/ProjetKeikenfinal/chatgptdemofront

# Set the working directory
WORKDIR /Users/macamara/Desktop/java-2022-12/ProjetKeikenfinal/chatgptdemofront

# Copy the package.json and package-lock.json
COPY package*.json ./

# Install the dependencies
RUN npm ci

# Copy the rest of the app's code
COPY . .

# Build the app
RUN npm run build

# Specify the command to run when the container starts
CMD [ "npm", "start" ]
