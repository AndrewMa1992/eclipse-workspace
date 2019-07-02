package cn.itcast.gjp.view;

import java.util.List;
import java.util.Scanner;

import cn.itcast.gjp.controller.ZhangWuController;
import cn.itcast.gjp.domain.ZhangWu;

public class MainView {

	private ZhangWuController controller = new ZhangWuController();

	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("------------------管家婆家庭记帐软件---------------");
			System.out.println("1.添加账务 2.编辑账务 3.删除账务 4.查询账务 5.退出系统");
			System.out.println("请输入要操作的功能序号[1-5]:");

			int choose = sc.nextInt();
			switch (choose) {
			case 1:
				addZhangWu();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				selectZhangWu();
				break;
			case 5:
				System.exit(0);
				break;

			}
		}
	}

	public void addZhangWu() {
		System.out.println("选择的是添加账务功能");
		Scanner sc = new Scanner(System.in);
		System.out.println("输入分类的名称");
		String flname = sc.next();
		System.out.println("输入金额");
		double money = sc.nextDouble();
		System.out.println("输入账户");
		String zhanghu = sc.next();
		System.out.println("输入日期:格式xxxx-xx-xx");
		String createtime = sc.next();
		System.out.println("输入具体描述");
		String description = sc.next();
		ZhangWu zw = new ZhangWu(0,flname, money, zhanghu, createtime, description);
		controller.addZhangWu(zw);
		System.out.println("恭喜添加账务成功");
	}
	public void selectZhangWu() {
		System.out.println("1.查询所有 2.按条件查询");
		Scanner sc = new Scanner(System.in);
		int selectChoose = sc.nextInt();

		switch (selectChoose) {
		case 1:
			selectAll();
			break;
		case 2:
			select();
			break;
		default:
			System.out.println("Wrong input!");
		}

	}
	

	public void selectAll() {
		List<ZhangWu> list = controller.selectAll();
		if(list.size() !=0)
		print(list);
		else
			System.out.println("No data has been found!");
	}


	public void select() {
		System.out.println("选择条件查询，输入日期的格式xxxx-xx-xx");
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入开始日期：");
		String startDate = sc.nextLine();
		System.out.println("请输入结果日期：");
		String endDate = sc.nextLine();
		List<ZhangWu> list = controller.select(startDate, endDate);
		if(list.size() !=0)
		print(list);
		else
			System.out.println("No data has been found!");
	}
	private void print(List<ZhangWu> list) {
		System.out.println("ID\t类别\t账户\t金额\t时间\t\t说明");
		for (ZhangWu zw : list) {
			System.out.println(zw.getZwid() + "\t" + zw.getFlname() + "\t" + zw.getZhanghu() + "\t" + zw.getMoney()
			+ "\t" + zw.getCreatetime() + "\t" + zw.getDescription());
		}
	}
}
