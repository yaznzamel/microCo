

### commands 

```
# build a jar file 
mvn clean package


# run a jar file
java -jar <jar-name>
```


```
Database configurations

apt install postgresql postgresql-contrib
systemctl start postgresql
sudo -u postgres psql

# create a database and user
ALTER USER postgres WITH ENCRYPTED PASSWORD 'AzureAdmin4';

# create new user
CREATE USER microco WITH ENCRYPTED PASSWORD 'AzureAdmin4';

# grant access to the database
GRANT ALL PRIVILEGES ON DATABASE microco TO microco;

CREATE DATABASE microco;
CREATE USER microco WITH ENCRYPTED PASSWORD 'AzureAdmin4';
GRANT ALL PRIVILEGES ON DATABASE microco TO microco;
\q


```