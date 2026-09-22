<div align="center">

<img src="NeoDungeonsMobs_title.png" alt="NeoDungeonsMobs_title" width="600" />

# NeoDungeonsMobs

基于 Minecraft NeoForge 平台的高版本移植模组，将《我的世界：地下城》（Minecraft Dungeons）中的丰富生物、强力首领、特色武器法杖与独特机制带入现代 Minecraft Java 版。

原项目地址：[https://github.com/firefoxsalesman/dungeonsmobs](https://github.com/firefoxsalesman/dungeonsmobs)

</div>

---

## 目录

- [简介](#简介)
- [运行与前置要求](#运行与前置要求)
- [主要内容与特性](#主要内容与特性)
  - [新增生物群系与生物分类](#新增生物群系与生物分类)
  - [首领与巨型怪物](#首领与巨型怪物)
  - [远古生物与生物附魔系统](#远古生物与生物附魔系统)
  - [特色装备与魔法武器](#特色装备与魔法武器)
- [开发与构建指南](#开发与构建指南)
- [鸣谢与致敬](#鸣谢与致敬)
- [开源许可协议](#开源许可协议)

---

## 简介

NeoDungeonsMobs 是针对 Minecraft NeoForge 环境的开源移植与现代化重构项目。

本项目继承了原作者 Patrigan 开发的经典模组 **Dungeons Mobs** 以及 Firefox Salesman 的移植成果，在保持《我的世界：地下城》原汁原味的生物行为、精致模型、粒子特效与音效设计的同时，全面适配 NeoForge 现代化模组架构与 GeckoLib 4 动画体系。

---

## 运行与前置要求

| 依赖项 | 必需版本 / 建议版本 | 说明 |
| :--- | :--- | :--- |
| Minecraft | 1.21.1 | 基础游戏版本 |
| NeoForge | 21.1.137 或更高版本 | 模组加载器 |
| Java | Java 21 (JDK 21) | 运行环境 |
| NeoDungeonsLibs | 1.4.0 或更高版本 | 必需前置基础库 |
| GeckoLib (NeoForge) | 4.7.4 或更高版本 | 必需实体动画引擎 |
| Curios API (NeoForge) | 9.0.0+ (建议 9.5.1+) | 可选前置，提供饰品支持 |

---

## 主要内容与特性

### 新增生物群系与生物分类

NeoDungeonsMobs 将地下城世界中的各类生物引入主世界、下界以及末地各个生物群系：

#### 亡灵与骷髅 (Undead & Skeletons)
- **丛林僵尸 (Jungle Zombie)**：栖息于茂密丛林，具有极强的侵略性。
- **冰冻僵尸 (Frozen Zombie)**：游荡于极寒群系，向目标投掷能够造成迟缓效果的雪球。
- **苔藓骷髅 (Mossy Skeleton)**：潜伏在丛林深处的致命射手，箭矢附带剧毒。
- **骷髅先锋 (Skeleton Vanguard)**：古代巫师君主的禁卫军，手持专属先锋盾牌，防御坚不可摧。
- **死灵法师 (Necromancer)**：掌握亡者秘术的施法者，可发射暗影法球并源源不断召唤僵尸仆从。
- **溺尸死灵法师 (Drowned Necromancer)**：深渊海域的统治者，可引导毁灭性的充能三叉戟风暴与蒸汽飞弹。
- **沉沦骷髅 (Sunken Skeleton)**：深海沉船与海沟中的水下骷髅，射出的箭矢具备特殊弹道与水下穿透力。
- **幽灵 (Wraith)**：在黑暗中滑翔漂浮的幽魂，可瞬移至目标身旁并在地面燃起大片幽灵之火。

#### 灾厄村民与劫掠部队 (Illagers & Raids)
- **皇家卫士 (Royal Guard)**：装备坚固胸甲与十字重盾的精锐灾厄步兵，能抵挡正面绝大多数直接伤害。
- **卫道士厨师 (Vindicator Chef)**：头戴厨师帽、身着围裙，挥舞木汤勺进行狂暴连续攻击的厨房守卫。
- **装甲掠夺者 (Armored Pillager)**：配备重型防具的高阶掠夺者，拥有极高的护甲防御与耐打击力。
- **装甲卫道士 (Armored Vindicator)**：身披板甲的狂暴斧手，冲锋速度与单次斩击威力惊人。
- **冰术师 (Iceologer)**：召唤悬浮在半空的巨型坚冰，砸向敌方造成范围粉碎性打击。
- **法师与分身 (Mage & Clones)**：掌握幻术与奥术飞弹的高阶施法者，能创造虚假分身迷惑对手。
- **地术师 (Geomancer)**：引导大地共振，升起阻断去路的花岗岩石墙，并生成延时引爆的地术师炸弹。
- **风术师 (Windcaller)**：身披翡翠袈裟，能呼唤强劲气流将敌人吹飞击退，并召唤上升旋风。
- **登山者 (Mountaineer)**：攀爬在雪山绝壁之上的灾厄猎人，熟练使用破冰镐进行凿击与攀爬。

#### 丛林植被生物 (Jungle Flora)
- **唤语者 (Whisperer)**：丛林深处的植物操控者，能在玩家脚下召唤致命蔓藤。
- **唤浪者 (Wavewhisperer)**：栖息在沿海湿地，掌控水生海葵与藤蔓的变种。
- **跃叶兽 (Leapleaf)**：庞大的丛林野兽，通过高高跃起后的砸地重击造成范围震荡。
- **速生藤蔓 (Quick Growing Vine)** 与 **毒刺藤蔓 (Poison Quill Vine)**：自地下突然钻出，缠绕目标并持续发射剧毒飞刺。
- **速生海带 (Quick Growing Kelp)** 与 **剧毒海葵 (Poison Anemone)**：水生植物防御陷阱，对误入的水下生物造成减速与持续伤害。

#### 下界异种 (Nether)
- **烈火恶魔 (Wildfire)**：环绕着四面浮动护盾的高阶烈焰生物，能发动大范围地面震荡波与密集火球轰炸。
- **掷菌者 (Fungus Thrower)** 与 **僵尸化掷菌者 (Zombified Fungus Thrower)**：背负真菌背包，向敌人投掷爆炸性蓝色下界菌的猪灵。

#### 末影族裔 (The End & Enderlings)
- **末影哨兵 (Endersent) / 执眼末影哨兵 (Eye Holder Endersent)**：体型庞大的末影巨兽，挥动巨臂施展毁灭重击。
- **疾爆者 (Blastling)**：末地远距离炮台生物，发射带有范围溅射与击退的疾爆弹。
- **窥视者 (Watchling)**：敏捷凶残的近战末地怪物，在暗影中潜行突袭。
- **陷阱者 (Snareling)**：远程喷吐黏性极高的陷阱球，使目标陷入纠缠状态无法移动。

---

### 首领与巨型怪物

- **红石傀儡 (Redstone Golem)**：高耸坚固的红石合金构装体，能够砸地并布设大范围引爆的红石地雷。
- **红石畸变体 (Redstone Monstrosity)**：炽热熔炉的终极产物，拥有庞大的生命体量、重拳粉碎攻击，并可自熔岩核心发射烈焰抛射物与红石立方。
- **哞菇畸变体 (Mooshroom Monstrosity)**：被真菌与孢子完全寄生同化的巨兽，喷射腐蚀孢子与蘑菇投射物。

---

### 远古生物与生物附魔系统

模组完整保留并实现了地下城风格的**远古生物机制**与**生物专属附魔（Mob Enchantments）**：

- **生物附魔种类**：
  - 燃烧 (Burning)、寒气 (Chilling)、决意 (Committed)、暴击 (Critical Hit)、双倍伤害 (Double Damage)
  - 回声 (Echo)、火迹 (Fire Trail)、重力脉冲 (Gravity Pulse)、治疗盟友 (Heals Allies)
  - 吸血 (Leeching)、漂浮射击 (Levitation Shot)、辉煌 (Radiance)、生命恢复 (Regeneration)
  - 迅捷 (Quick)、速度窃取 (Tempo Theft)、疾冲 (Rush)、虚弱 (Weakening)
- **远古生物挑战**：
  - 拥有极其强大的词缀组合与仆从护卫集群。
  - 支持通过管理员指令召唤特异远古生物（`/summonuniqueancient`）与非特异远古生物（`/summonnonuniqueancient`）。

---

### 特色装备与魔法武器

- **法杖与神器**：
  - **死灵法师三叉戟 (Necromancer Trident)**：引落 8 支充能三叉戟，引发雷暴狂潮。
  - **死灵法师法杖 (Necromancer Staff)**：消耗灵魂之力召唤忠诚的僵尸仆从协助战斗。
  - **风术师法杖 (Windcaller Staff)**：释放冲击飓风，击退正面敌人。
  - **地术师法杖 (Geomancer Staff)**：向地面传导震颤，召唤阻挡走位的石柱与爆炸柱。
  - **冰霜魔杖 (Ice Wand)**：凝结千钧巨冰从天而降压碎目标。
- **武器与盾牌**：
  - **破冰镐系列**：包含普通破冰镐、坚固破冰镐与强化破冰镐，兼备开掘与破甲性能。
  - **皇家卫士盾牌 (Royal Guard Shield)**：具备 16 种原版染料的染色支持，提供坚实的高额格挡防御。
  - **先锋盾牌 (Vanguard Shield)**：古老帝国的坚固防具，抗击退性能优异。
  - **木汤勺 (Wooden Ladle)**：充满趣味但兼具实战连续击退效果的餐具武器。
- **各职业护甲套装**：
  - 法师套装 (Mage Armor)、皇家卫士套装 (Royal Guard Armor)、登山服套装 (Mountaineer Armor)、风术师袈裟 (Windcaller Armor)、厨师服 (Chef Armor)、掠夺者头盔与卫道士角盔等。

---

## 开发与构建指南

### 环境准备
1. 安装并配置 **Java 21 (JDK 21)**。
2. 推荐使用 **IntelliJ IDEA** 或 **VS Code** 进行开发。

### 构建步骤
使用仓库自带的 Gradle Wrapper 执行构建任务：

```bash
# 检查依赖与编译代码
./gradlew build

# 仅构建发布用的 Mod Jar 文件
./gradlew jar
```

构建完成的 Jar 文件将保存在 `build/libs/` 目录下。

### 运行调试客户端与服务端
```bash
# 启动 NeoForge 调试客户端
./gradlew runClient

# 启动 NeoForge 测试服务端
./gradlew runServer

# 运行数据生成器 (Datagen)
./gradlew runData
```

---

## 鸣谢与致敬

- **Mojang Studios**：创作了 Minecraft 与 Minecraft Dungeons 原作及其艺术设定。
- **Patrigan**：Dungeons Mobs 与 Dungeons Libraries 的原作者，奠定了模组扎实的基础架构与模型体系。
- **Firefox Salesman**：维护并完成了 Dungeons Mobs 早期移植版本：[https://github.com/firefoxsalesman/dungeonsmobs](https://github.com/firefoxsalesman/dungeonsmobs)。
- **GeckoLib Team**：提供了强大的跨平台实体骨骼动画引擎。
- **NeoForged Team**：提供了现代、高效且活跃的 NeoForge 模组加载器与开发工具链。

---

## 开源许可协议

NeoDungeonsMobs 继承原项目的开源协议，遵循 **MIT 许可证**。

保留原作者 Patrigan 的版权声明与许可条款：

```text
MIT License

Copyright (c) 2022 Patrigan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

有关完整协议详情，请参阅仓库根目录下的 [LICENSE.txt](LICENSE.txt)。
