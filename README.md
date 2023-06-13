# sx3-WMS

#### 介绍

仓库管理系统

#### 成员及工作占比

黄南烨 20231069 27%
毛景辉 20301015 25%
申子涵 20301018 23%
王逸杰 20301023 15%
王宣琪 20301022 10%

#### 软件架构

软件架构说明

数据库设计

```sql
-- 创建user表
CREATE TABLE user
(
    user_id     INT      NOT NULL AUTO_INCREMENT,
    user_name   VARCHAR(50) UNIQUE,
    password    VARCHAR(50),
    type        VARCHAR(50),
    create_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
);
```

```sql
-- 创建product表
CREATE TABLE product
(
    product_id   INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100) UNIQUE,
    supplier_id  INT,
    PRIMARY KEY (product_id),
    CONSTRAINT fk_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建supplier表
CREATE TABLE supplier
(
    supplier_id      INT NOT NULL AUTO_INCREMENT,
    supplier_name    VARCHAR(50),
    supplier_address VARCHAR(100),
    supplier_phone   VARCHAR(20),
    PRIMARY KEY (supplier_id)
);
```

```sql
-- 创建stock表
CREATE TABLE stock
(
    stock_id       INT NOT NULL AUTO_INCREMENT,
    product_id     INT,
    stock_quantity INT,
    position       VARCHAR(50),
    PRIMARY KEY (stock_id),
    CONSTRAINT fk_product_stock FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建in_stock表
CREATE TABLE in_stock
(
    in_stock_id       INT      NOT NULL AUTO_INCREMENT,
    in_stock_time     DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id           INT,
    in_stock_status   VARCHAR(50),
    in_stock_category VARCHAR(50),
    PRIMARY KEY (in_stock_id),
    CONSTRAINT fk_user_in_stock FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建in_stock_item表
CREATE TABLE in_stock_item
(
    in_stock_item_id  INT NOT NULL AUTO_INCREMENT,
    in_stock_id       INT,
    product_id        INT,
    in_stock_quantity INT,
    in_stock_price    DECIMAL(10, 2),
    PRIMARY KEY (in_stock_item_id),
    CONSTRAINT fk_in_stock FOREIGN KEY (in_stock_id) REFERENCES in_stock (in_stock_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_product_in_stock FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建out_stock表
CREATE TABLE out_stock
(
    out_stock_id       INT      NOT NULL AUTO_INCREMENT,
    out_stock_time     DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id            INT,
    out_stock_status   VARCHAR(50),
    out_stock_category VARCHAR(50),
    PRIMARY KEY (out_stock_id),
    CONSTRAINT fk_user_out_stock FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建out_stock_item表
CREATE TABLE out_stock_item
(
    out_stock_item_id  INT NOT NULL AUTO_INCREMENT,
    out_stock_id       INT,
    product_id         INT,
    out_stock_quantity INT,
    out_stock_price    DECIMAL(10, 2),
    PRIMARY KEY (out_stock_item_id),
    CONSTRAINT fk_out_stock FOREIGN KEY (out_stock_id) REFERENCES out_stock (out_stock_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_product_out_stock FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建orders表
CREATE TABLE orders
(
    orders_id        INT NOT NULL AUTO_INCREMENT,
    orders_time      DATETIME,
    user_id          INT,
    orders_status    VARCHAR(50),
    total_price      DECIMAL(10, 2),
    payment_method   VARCHAR(50),
    shipping_address VARCHAR(100),
    PRIMARY KEY (orders_id),
    CONSTRAINT fk_user_orders FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

```sql
-- 创建orders_item表
CREATE TABLE orders_item
(
    orders_item_id INT NOT NULL AUTO_INCREMENT,
    orders_id      INT,
    product_id     INT,
    quantity       INT,
    price          DECIMAL(10, 2),
    PRIMARY KEY (orders_item_id),
    CONSTRAINT fk_orders FOREIGN KEY (orders_id) REFERENCES orders (orders_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_product_orders FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5. Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
