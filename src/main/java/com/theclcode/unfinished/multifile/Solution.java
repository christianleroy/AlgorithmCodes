package com.theclcode.unfinished.multifile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static BufferedReader br;
	private static UserSolution usersolution = new UserSolution();

	private static char[] toCharArray(String s)
	{
		char[] ca = new char[s.length()+1];
		for(int i=0; i<s.length(); i++)
			ca[i] = s.charAt(i);
		ca[s.length()] = 0;
		return ca;
	}

	private static int run(int Ans) throws IOException
	{
		int n = Integer.parseInt(br.readLine());
		usersolution.init();

		char[] userName;
		char[] groupName;
		char[] path;
		char[] directoryName;
		int permission;
		int expectedAnswer;
		char[] fileName;
		char[] fileExt;
		char[] pattern;

		for(int i=0; i<n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());

			if(type == 1)
			{
				userName = toCharArray(st.nextToken());
				groupName = toCharArray(st.nextToken());

				usersolution.createUser(userName, groupName);
			}
			else if(type == 2)
			{
				userName = toCharArray(st.nextToken());
				path = toCharArray(st.nextToken());
				directoryName = toCharArray(st.nextToken());
				permission = Integer.parseInt(st.nextToken());

				int userAnswer = usersolution.createDirectory(userName, path, directoryName, permission);
				expectedAnswer = Integer.parseInt(st.nextToken());
				if(userAnswer != expectedAnswer)
					Ans = 0;
			}
			else if(type == 3)
			{
				userName = toCharArray(st.nextToken());
				path = toCharArray(st.nextToken());
				fileName = toCharArray(st.nextToken());
				fileExt = toCharArray(st.nextToken());

				int userAnswer = usersolution.createFile(userName, path, fileName, fileExt);
				expectedAnswer = Integer.parseInt(st.nextToken());
				if(userAnswer != expectedAnswer)
					Ans = 0;
			}
			else if(type == 4)
			{
				userName = toCharArray(st.nextToken());
				pattern = toCharArray(st.nextToken());

				int userAnswer = usersolution.find(userName, pattern);
				expectedAnswer = Integer.parseInt(st.nextToken());
				if(userAnswer != expectedAnswer)
					Ans = 0;
			}
		}

		return Ans;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int TC = Integer.parseInt(st.nextToken());
		int Ans = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			System.out.println("#" + testcase + " " + run(Ans));
		}

		br.close();
	}
}