package com.hp.bean;

public class User {
		private int userId;// ������������
		private String account;// �����˺�
		private String password;// ��������
		private String name;// �û�����
		private int gender;// �Ա�1���� 0��Ů
		private String contact;// ��ϵ��ʽ
		private String remark;// ��ע

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getGender() {
			return gender;
		}

		public void setGender(int gender) {
			this.gender = gender;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", account=" + account
					+ ", password=" + password + ", name=" + name + ", gender="
					+ gender + ", contact=" + contact + ", remark=" + remark
					+ "]";
		}

		public User(int userId, String account, String password, String name,
				int gender, String contact, String remark) {
			super();
			this.userId = userId;
			this.account = account;
			this.password = password;
			this.name = name;
			this.gender = gender;
			this.contact = contact;
			this.remark = remark;
		}

	

}
