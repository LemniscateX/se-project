# 软件接口说明



### 1. 用户页面接口

#### 1.1 首页

| 接口说明 |                |
| -------- | -------------- |
| 接口地址 | http://IP:8080 |
| 请求方式 | GET            |



#### 1.2 注册

| 接口说明 |                                                             |
| -------- | ----------------------------------------------------------- |
| 接口地址 | http://IP:8080/user/register                                |
| 请求方式 | POST/GET                                                    |
| 请求示例 | http://IP:8080/user/register?email=sampleEmail&password=123 |

| 请求参数说明 |          |          |          |
| ------------ | -------- | -------- | -------- |
| **名称**     | **必填** | **类型** | **说明** |
| email        | 是       | String   | 用户账号 |
| password     | 是       | String   | 用户密码 |



#### 1.3 登录

| 接口说明 |                                                          |
| -------- | -------------------------------------------------------- |
| 接口地址 | http://IP:8080/user/login                                |
| 请求方式 | POST/GET                                                 |
| 请求示例 | http://IP:8080/user/login?email=sampleEmail&password=123 |

| 请求参数说明 |          |          |          |
| ------------ | -------- | -------- | -------- |
| **名称**     | **必填** | **类型** | **说明** |
| email        | 是       | String   | 用户账号 |
| password     | 是       | String   | 用户密码 |



#### 1.4 主页

| 接口说明 |                           |
| -------- | ------------------------- |
| 接口地址 | http://IP:8080/user/index |
| 请求方式 | GET                       |



#### 1.5 浏览商品

| 接口说明 |                              |
| -------- | ---------------------------- |
| 接口地址 | http://IP:8080/user/products |
| 请求方式 | GET                          |



#### 1.6 我的订单

| 接口说明 |                           |
| -------- | ------------------------- |
| 接口地址 | http://IP:8080/user/bills |
| 请求方式 | GET                       |





#### 1.7 订单填写

| 接口说明 |                                                   |
| -------- | ------------------------------------------------- |
| 接口地址 | http://IP:8080/user/makeBill                      |
| 请求方式 | POST                                              |
| 请求示例 | http://IP:8080/user/makeBill?productId=1&userId=1 |

| 请求参数说明 |          |          |          |
| ------------ | -------- | -------- | -------- |
| **名称**     | **必填** | **类型** | **说明** |
| productId    | 是       | Integer  | 用户账号 |
| userId       | 是       | Integer  | 用户编号 |



#### 1.8 订单提交

| 接口说明 |                                                              |
| -------- | ------------------------------------------------------------ |
| 接口地址 | http://IP:8080/user/submitBill                               |
| 请求方式 | POST                                                         |
| 请求示例 | http://IP:8080/user/submitBill?productId=1&userId=1&productCount=3&receiverName=name&receiverTel=110&receiverAddress=Guangzhou |

| 请求参数说明    |              |          |            |
| --------------- | ------------ | -------- | ---------- |
| **名称**        | **必填**     | **类型** | **说明**   |
| productId       | 是(隐式传递) | Integer  | 商品编号   |
| userId          | 是(隐式传递) | Integer  | 用户编号   |
| productCount    | 是           | Integer  | 商品数量   |
| receiverName    | 是           | String   | 收件人姓名 |
| receiverTel     | 是           | String   | 收件人电话 |
| receiverAddress | 是           | String   | 收件人地址 |



#### 1.9 确认收货

| 接口说明 |                                       |
| -------- | ------------------------------------- |
| 接口地址 | http://IP:8080/user/bill/receive      |
| 请求方式 | POST                                  |
| 请求示例 | http://IP:8080/user/bill/receive?id=1 |

| 请求参数说明 |          |          |          |
| ------------ | -------- | -------- | -------- |
| **名称**     | **必填** | **类型** | **说明** |
| id           | 是       | Integer  | 订单编号 |



### 2. 管理页面接口

#### 2.1 登录

| 接口说明 |                                                        |
| -------- | ------------------------------------------------------ |
| 接口地址 | http://IP:8080/admin                                   |
| 请求方式 | POST/GET                                               |
| 请求示例 | http://IP:8080/user/admin?account=admin&password=admin |

| 请求参数说明 |          |          |            |
| ------------ | -------- | -------- | ---------- |
| **名称**     | **必填** | **类型** | **说明**   |
| account      | 是       | String   | 管理员账号 |
| password     | 是       | String   | 管理员密码 |



#### 2.2 主页

| 接口说明 |                            |
| -------- | -------------------------- |
| 接口地址 | http://IP:8080/admin/index |
| 请求方式 | GET                        |



#### 2.3管理商品

| 接口说明 |                               |
| -------- | ----------------------------- |
| 接口地址 | http://IP:8080/admin/products |
| 请求方式 | GET                           |



#### 2.4 管理订单

| 接口说明 |                            |
| -------- | -------------------------- |
| 接口地址 | http://IP:8080/admin/bills |
| 请求方式 | GET                        |



#### 2.5 管理用户

| 接口说明 |                            |
| -------- | -------------------------- |
| 接口地址 | http://IP:8080/admin/users |
| 请求方式 | GET                        |



#### 2.6 添加商品

| 接口说明 |                                                              |
| -------- | ------------------------------------------------------------ |
| 接口地址 | http://IP:8080/admin/products/add                            |
| 请求方式 | POST                                                         |
| 请求示例 | http://IP:8080/admin/products/add?name=pot&price=2000&quantity=20&description=good&category=kitchen&file=image.png |

| 请求参数说明 |          |               |          |
| ------------ | -------- | ------------- | -------- |
| **名称**     | **必填** | **类型**      | **说明** |
| name         | 是       | String        | 商品名称 |
| price        | 是       | Double        | 商品价格 |
| quantity     | 是       | Integer       | 商品数量 |
| description  | 是       | String        | 商品描述 |
| category     | 是       | String        | 商品类别 |
| file         | 是       | MultipartFile | 商品图片 |



#### 2.7 修改商品

| 接口说明 |                                                              |
| -------- | ------------------------------------------------------------ |
| 接口地址 | http://IP:8080/admin/products/modify                         |
| 请求方式 | POST                                                         |
| 请求示例 | http://IP:8080/admin/products/modify?id=1&modifiedName=pot&modifiedPrice=2000&modifiedQuantity=20&modifiedDescription=good&modifiedCategory=kitchen |

| 请求参数说明        |          |          |                |
| ------------------- | -------- | -------- | -------------- |
| **名称**            | **必填** | **类型** | **说明**       |
| id                  | 是       | Integer  | 商品编号       |
| modifiedName        | 是       | String   | 修改的商品名称 |
| modifiedPrice       | 是       | Double   | 修改的商品价格 |
| modifiedQuantity    | 是       | Integer  | 修改的商品数量 |
| modifiedDescription | 是       | String   | 修改的商品描述 |
| modifiedCategory    | 是       | String   | 修改的商品类别 |



#### 2.8 删除商品

| 接口说明 |                                           |
| -------- | ----------------------------------------- |
| 接口地址 | http://IP:8080/admin/products/delete      |
| 请求方式 | POST                                      |
| 请求示例 | http://IP:8080/admin/products/delete?id=2 |

| 请求参数说明 |          |          |          |
| ------------ | -------- | -------- | -------- |
| **名称**     | **必填** | **类型** | **说明** |
| id           | 是       | Integer  | 商品编号 |



#### 2.9 发货

| 接口说明 |                                     |
| -------- | ----------------------------------- |
| 接口地址 | http://IP:8080/admin/bill/ship      |
| 请求方式 | POST                                |
| 请求示例 | http://IP:8080/admin/bill/ship?id=1 |

| 请求参数说明 |          |          |          |
| ------------ | -------- | -------- | -------- |
| **名称**     | **必填** | **类型** | **说明** |
| id           | 是       | Integer  | 订单编号 |