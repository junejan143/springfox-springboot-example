## Paths
### 创建用户
```
POST /user
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|BodyParameter|user|user|true|User||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|201|Created|integer (int32)|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* User

### 获取某个用户的信息
```
GET /user/{userId}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|userId|userId|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|User|
|500|500 message|Error|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* User
