# 项目说明

* 项目启动就会在当前路径下创建几个文件夹和文件
  * data/new-data目录:用于存放处理后的新账单文件文件
  * data/task-dta目录:用于存放定时任务执行期间产生的一些文件
  * data/task-dta/handle-err-file.txt文件：记录处理失败的账单文件名
  * data/task-dta/handle-success-file.txt：记录处理成功的账单文件名
  * data/task-dta/job-execute-result.txt文件：记录定时日任务直接结果数据【便于后期做页面展示】
  
* classpath下的old-data文件夹，用于模拟ftp文件服务器，用于存放生成的账单文件

* 账单文件名格式为：userId-日期时间-bill.txt

* 在命令行按住Ctrl+C,程序退出会清除data文件夹以及该文件夹里面的所有内容


 # 使用说明
 





