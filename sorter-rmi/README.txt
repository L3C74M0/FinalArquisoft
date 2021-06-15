frascati compile mq1/src client
frascati compile mq2/src worker
frascati run mq1 -libpath client.jar -s r -m run
frascati run mq2 -libpath worker.jar -s r -m run
frascati run mq3 -libpath worker.jar -s r -m run
frascati run mq4 -libpath worker.jar -s r -m run