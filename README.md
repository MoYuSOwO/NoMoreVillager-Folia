# NoMoreVillager
村民计划生育（误）

## 说明

不知道各位服主有没有遇到过服务器发展到一定程度后，明明在线人数不多，也没有什么红石机器，但是服务器tps缺掉的厉害。

查看timings，发现在WorldDoTick项中的Entities分类里有个Villager分类占用了大量的计算时间（有的时候甚至达到了30%）。
由于这破游戏的大量机制都依赖村民，所以玩家一般会做出**自动繁殖机**这样的东西刷出大量的村民。
由于是自动繁殖机，所以玩家往往根本不会意识到他们搞出来了大量的村民。

这就导致了两个非常可怕的问题：
1. 村民繁殖机往往会使用水流让村民卡墙角，这就会造成大量的实体挤压引起卡顿；
2. 由于1.14版本开始加入工作方块，刚成年的村民为无业村民，而无业村民默认会以每5tick一次的频率搜寻附近的工作方块尝试工作，这就会导致大量的无业村民在服务器上搜寻工作方块，占用大量的计算时间。

## 功能介绍

1. 当一个区域内（4×4 chunks）的无业村民达到了一定限制，不再允许新的村民繁殖出来；
2. 当一个区域内（4×4 chunks）的无业村民超过了一定限制，会强制处决过多的无业村民；

## 支持版本

- 1.14 及以上 Bukkit/Spigot/Paper

## 使用方法

1. 将插件放入服务器的 `plugins` 目录下
2. 重启服务器
3. 在 `plugins/NoMoreVillager/config.yml` 中配置
4. 控制台或OP输入 `/nmv reload` 重载配置

> 也可以不修改配置文件，直接使用指令进行设置。
>
> 使用指令设置后也会同步更新到配置文件。

## 指令

### OP指令

`/nmv reload` 重载配置文件。

`/nmv limit <int>` 设置繁殖上限。

`/nmv forceDelete <int>` 设置强制处决阈值。

### 玩家指令

无

## 配置文件参考

```yaml
# 一定区域内无业村民上限
Limit: 3

# 超过多少就强制删除
ForceDelete: 6
```
