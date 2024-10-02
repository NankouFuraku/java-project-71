.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions

json:
	./build/install/app/bin/app src/main/resources/fixtures/file1.json src/main/resources/fixtures/file2.json

json-plain:
	./build/install/app/bin/app -f plain src/main/resources/fixtures/file1.json src/main/resources/fixtures/file2.json

json-stylish:
	./build/install/app/bin/app -f stylish src/main/resources/fixtures/file1.json src/main/resources/fixtures/file2.json

yaml:
	./build/install/app/bin/app src/main/resources/fixtures/file1.yaml src/main/resources/fixtures/file2.yaml

yaml-stylish:
	./build/install/app/bin/app -f stylish src/main/resources/fixtures/file1.yaml src/main/resources/fixtures/file2.yaml

yaml-plain:
	./build/install/app/bin/app -f plain src/main/resources/fixtures/file1.yaml src/main/resources/fixtures/file2.yaml

json-json:
	./build/install/app/bin/app -f json src/main/resources/fixtures/file1.json src/main/resources/fixtures/file2.json

yaml-json:
	./build/install/app/bin/app -f json src/main/resources/fixtures/file1.yaml src/main/resources/fixtures/file2.yaml

build-run: build run

.PHONY: build