#include <cstdio>

/**************** START OF USER SOLUTION ****************/

void init(int N, char init_string[]) {

}

int change(char string_A[], char string_B[]) {
	
	return 0;
}

void result(char ret[]) {
	
}

/***************** END OF USER SOLUTION *****************/


static int mSeed = 5;

static const int MAXL = 50005;
static const int DUMMY_LEN = 5959;
static int score = 0;
static int total_score = 0;

static char dummy1[DUMMY_LEN];
static char init_string[MAXL];
static char dummy2[DUMMY_LEN];
static char result_string[MAXL];
static char dummy3[DUMMY_LEN];
static char user_ans_string[MAXL];
static char dummy4[DUMMY_LEN];
static char string_A[4];
static char string_B[4];
static int init_string_len = 0;
static int char_type = 0;
static int query_cnt = 0;

int pseudo_rand()
{
	mSeed = mSeed * 214013 + 2531011;
	return (mSeed >> 16) & 0x7FFF;
}

int main() {
	int T;
	scanf("%d", &T);

	total_score = 0;
	for (int TC = 1; TC <= T; TC++) {
		printf("Case #%d:\n", TC);

		score = 100;
		scanf("%d", &mSeed);
		scanf("%d", &init_string_len);
		scanf("%d", &char_type);
		scanf("%d", &query_cnt);
		
		for (int i = 0; i < init_string_len; i++)
		{
			init_string[i] = (char)(pseudo_rand() % char_type + 'a');
		}
		init_string[init_string_len] = '\0';

		init(init_string_len, init_string);

		string_A[3] = string_B[3] = '\0';
		for (int i = 0; i < query_cnt; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				string_A[k] = (char) ((pseudo_rand() % char_type) + 'a');
				string_B[k] = (char) ((pseudo_rand() % char_type) + 'a');
			}
			int user_ans = change(string_A, string_B);
			printf("%d\n", user_ans);
		}

		for (int i = 0; i < init_string_len; i++)
			user_ans_string[i] = ' ';
		result(user_ans_string);
		for (int i = 0; i < init_string_len; i++)
			printf("%c", user_ans_string[i]);
		printf("\n");
	}
	return 0;
}
