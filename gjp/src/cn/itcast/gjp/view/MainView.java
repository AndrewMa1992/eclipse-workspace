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
			System.out.println("------------------�ܼ��ż�ͥ�������---------------");
			System.out.println("1.������� 2.�༭���� 3.ɾ������ 4.��ѯ���� 5.�˳�ϵͳ");
			System.out.println("������Ҫ�����Ĺ������[1-5]:");

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
		System.out.println("ѡ��������������");
		Scanner sc = new Scanner(System.in);
		System.out.println("������������");
		String flname = sc.next();
		System.out.println("������");
		double money = sc.nextDouble();
		System.out.println("�����˻�");
		String zhanghu = sc.next();
		System.out.println("��������:��ʽxxxx-xx-xx");
		String createtime = sc.next();
		System.out.println("�����������");
		String description = sc.next();
		ZhangWu zw = new ZhangWu(0,flname, money, zhanghu, createtime, description);
		controller.addZhangWu(zw);
		System.out.println("��ϲ�������ɹ�");
	}
	public void selectZhangWu() {
		System.out.println("1.��ѯ���� 2.��������ѯ");
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
		System.out.println("ѡ��������ѯ���������ڵĸ�ʽxxxx-xx-xx");
		Scanner sc = new Scanner(System.in);
		System.out.println("�����뿪ʼ���ڣ�");
		String startDate = sc.nextLine();
		System.out.println("�����������ڣ�");
		String endDate = sc.nextLine();
		List<ZhangWu> list = controller.select(startDate, endDate);
		if(list.size() !=0)
		print(list);
		else
			System.out.println("No data has been found!");
	}
	private void print(List<ZhangWu> list) {
		System.out.println("ID\t���\t�˻�\t���\tʱ��\t\t˵��");
		for (ZhangWu zw : list) {
			System.out.println(zw.getZwid() + "\t" + zw.getFlname() + "\t" + zw.getZhanghu() + "\t" + zw.getMoney()
			+ "\t" + zw.getCreatetime() + "\t" + zw.getDescription());
		}
	}
}
