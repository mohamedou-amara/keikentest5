FROM nginx:latest

COPY nginx.conf /etc/nginx/nginx.conf

RUN chown root:root /etc/nginx/nginx.conf

CMD ["nginx", "-g", "daemon off;"]
