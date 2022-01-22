.PHONY: test

setup:
	lein deps

test:
	lein test

ci: test

docker-ci:
	docker-compose -f ci/docker-compose.yml -p rocket-link-ci build
	docker-compose -f ci/docker-compose.yml -p rocket-link-ci run app make setup
	docker-compose -f ci/docker-compose.yml -p rocket-link-ci run app make ci
