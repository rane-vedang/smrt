# SMRT App

SMRT Application provides the shortest path between 2 MRT stations. The application runs on spring boot and is built using maven. There are 2 APIs. The /shortestpath API returns the number of stations or hops required to reach from the source to the destination. The /shortestpathtime API  returns the time taken to reach the destination based on the start time and also considers peak hours and night hours as given in the problem description.

## Installation
There are 2 ways to install and run the application as given below:

### Installation via Fat Jar

1. Download the fat jar from https://drive.google.com/file/d/10W3EU6VpIx_o2hBSZWv0deCugycCt2GY/view?usp=sharing and unzip

```bash
unzip 'smrt_vedang.zip'
cd smrt_vedang
```

2. Run run_fat_jar.sh script that installs jdk 8 and runs the fat jar.
```bash
sudo ./run_fat_jar.sh
```


### Installation via building the code (if there is no access to the google drive link mentioned above)

1. Install Java 8.
```bash
sudo apt install openjdk-8-jdk
```
2. Install git
```bash
sudo apt install git
```
3. Install maven
```bash
sudo apt install maven
```
4. Clone the git project (https://github.com/rane-vedang/smrt)
```bash
git clone https://github.com/rane-vedang/smrt.git
```
5. Run the app
```bash
cd smrt
mvn spring-boot:run
```

## Usage

### API for shortest path stations between source and destination
http://localhost:8080/shortestpath?source=Tuas%20Link&destination=Changi%20Airport



### API for shortest path with time between source and destination
http://localhost:8080/shortestpathtime?source=Boon%20Lay&destination=Little%20India&startTime=2020-10-29%2013:30
