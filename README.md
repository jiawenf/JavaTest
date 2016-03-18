# 项目


######  为什么由svn转向git
[GIT和SVN之间的五个基本区别](http://www.oschina.net/news/12542/git-and-svn)

[为什么 Git 比 SVN 好](http://blog.jobbole.com/20069/)

######  git入门
[使用git和github进行协同开发流程](http://segmentfault.com/a/1190000002413519)

[git - 简易指南](http://www.bootcss.com/p/git-guide/)

[Git教程](http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/)

# 快速开始
### 0.FORK

微农使用 GITHUB 作为 git 版本控制的远程仓库, 开发人员自行fork相应的项目, 所有的操作都在自己fork的仓库中操作.

## 1 REMOTE管理

开发人员在开发时, 使用自己fork的项目作为本地的`origin`, 同时需要添加MicroFarmer的官方remote 命名为`upstream`

涉及到的命令:
```
git clone https://github.com/[username]/weizhang.git # [username] 这块修改成自己的github账号
git remote add upstream https://github.com/MicroFarmer/weizhang.git
```

## 2 BRANCH管理

官方库使用 `develop` 分支来跟踪代码, 大家同步代码的时候请同步`develop`分支.

fork库中的分支由开发人员自行管理, 管理原则如下:

* master为最新的稳定主分支代码
* develop为开发分支
* feature-开头为特定需求分支

# 1. 开发新功能

从同步后的develop分支拉出一个以`feature-`开头的分支, 在此分支上提交此功能相关的代码.

涉及到的命令:
```
git checkout develop  // 切到develop
git pull upstream develop  // 拉线上最上的最新代码
git checkout -b feature-xxxx // 切一个干净的分支
git add -A :/ // 接交所有修改
git commit -m 'commit' // 对本次提交的修改写上描述
git push origin feature-xxxx // 推到自己的远程仓库
```

# 2. 修bug

不管当前在开发什么项目, 开发人员都必须重新切到develop分支拉出一个以`fix-`开头的分支, 来进行bug的修复.

开发中的代码未完成时, 采用`git stash`或者临时提交之后切换到develop分支.
如果是`git stash`之后切换到develop的, 在修复完bug之后切换到之前的分支之后再用`git stash pop`把之前压栈的代码提取出来.
如果是使用了`git commit`之后切换到develop的, 在修复完bug之后切换到之前的分支继续开发过程时, 可以使用`git commit --amend`来合并之前的临时提交, 避免产生不可用的提交ID.


# 3. 合作开发

涉及到多个开发人员协作的项目, 选定其中一人为小组长. 小组长在自己的fork中创建以该项目命名的分支. 小组中其他开发人员都添加一个该小组长的repo为自己的一个REMOTE, 并命名为小组长的名字.
在合作开发过程中, 开发人员以小组长的fork为临时的origin来推拉代码.

涉及到的命令:
```
git checkout develop
git checkout -b feature-xxxx  // 新切一feature-xxxx分支
git remote add [wn518] git@github.com:[wn518]/juyouhui.git // 添加wn518的远程仓库地址
git pull wn518 feature-xxxx  // 拉wn518的feature-xxxx上的代码
git push origin feature-xxxx  // 推到自己的远程仓库

```

# PULL REQUEST

## 1. 项目开发和修BUG

这两个操作是一样的上线流程, 开发人员将自己的分支推到自己的远程仓库`origin`中, 在GITHUB上建立一个`origin/feature-xxxx` 到 `upstream/develop` 的pull request.
并告知代码审核人员, 审核该pull request, 并进行上线操作.

## 2. 合作开发

由小组长负责将所有代码汇总到自己的fork中的对应分支, 然后小组长走单人开发的pull request流程.


# GIT 推荐配置

配置文件位置 `~/.gitconfig`
```
[http]
sslverify = false
[core]
editor = vim
[merge]
tool = vimdiff
[diff]
tool = vimdiff
[alias]
br = branch
co = checkout
ci = commit
st = status
[color]
ui = true
[gui]
encoding = utf-8
[difftool]
prompt = false
```
