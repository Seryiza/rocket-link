.PHONY: test

setup:
	lein deps

test:
	lein test

ci: setup test
