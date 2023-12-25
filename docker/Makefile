init:
	docker compose up --build -d

destroy:
	docker compose down --rmi all --volumes --remove-orphans

remake:
	@make destroy
	@make init
