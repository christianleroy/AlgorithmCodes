package com.theclcode.unfinished.medianoftwosortedarrays;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {

        int[][][] testCases =
                {{
                        {3, 13, 19, 27, 29}, //30
                        {31, 32, 45, 51, 90}
                }, {
                        {1},
                        {2, 4, 3} // 3
                }, {
                        {1, 2},
                        {3, 4, 5} // 3
                }, {
                        {2, 4},
                        {6, 7, 9} // 6
                }, {
                        {4, 6, 11},
                        {5, 10, 19} // 8
                }, {
                        {1, 3}, // 2
                        {2}
                }, {
                        {1, 2},
                        {3, 4} // 2.5
                }, {
                        {1},
                        {4, 7, 9} // 5.5
                }, {
                        {3, 4}, // 12.5
                        {11, 14, 19, 22}
                }, {
                        {3},
                        {-2, -1} // -1
                }, {
                        {2, 5, 7, 8},
                        {10, 13, 15, 20} // 9
                }, {
                        {2, 4, 6, 8},
                        {1, 3, 5, 7} //4.5
                }, {
                        {1, 1, 3, 3},
                        {1, 1, 3, 3} //2
                }, {
                        {6, 7, 7, 9, 10},
                        {2, 5, 9, 10, 11} //8
                }, {
                        {5},
                        {4} // 4.5
                },{
                        {},{5} //5
                }, {
                        {11, 19},
                        {45, 49, 50} // 45
                }, {
                        {11, 19},
                        {8, 19, 20, 22} //19
                }, {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, // 10
                        {11, 12, 13, 14, 15, 16, 17, 18, 19},
                }, {
                        {1, 1, 3, 3, 3, 3},
                        {4, 5, 6, 7, 8} // 3
                }, {
                        {1, 1, 3, 3, 3, 3},
                        {1, 1, 3, 3} //3
                }, {
                        {1, 2, 3, 4, 5, 7, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15 ,16, 17, 18, 19, 20} //10.5
                }, {
                        {1, 2, 3},
                        {4, 5, 6, 7, 8} //4.5
                }
                };


        for (int[][] testCase : testCases) {
            int[] a = testCase[0];
            int[] b = testCase[1];
            double result = findMedian(a, b, 0, a.length - 1, 0, b.length - 1);
            System.out.println(result);
        }
    }


    static double findMedian(int[] m, int[] n, int mStart, int mEnd, int nStart, int nEnd) {

        int mLength = (mEnd - mStart) + 1;
        int nLength = (nEnd - nStart) + 1;

        if (mLength > nLength) {
            return findMedian(n, m, nStart, nEnd, mStart, mEnd);
        }

//        System.out.println(Arrays.toString(Arrays.copyOfRange(m, mStart, mEnd + 1)));
//        System.out.println(Arrays.toString(Arrays.copyOfRange(n, nStart, nEnd + 1)));
        if (mLength == 0) {
            return computeMedian(n, nStart, nEnd, nLength);
        } else if (mLength == 1) {
            if (nLength == 1) {
                return (double) (m[mStart] + n[nStart]) / 2;
            } else if (nLength % 2 == 0) {
                if (nLength == 2) {
                    int max = Math.max(m[mEnd], n[nEnd]);
                    int min = Math.min(m[mStart], n[nStart]);
                    return (double) (m[mStart] + n[nStart] + n[nEnd] - max - min);
                } else {
                    int midIdx = getMidIdx(nStart, nEnd);
                    int beforeMidIdx = midIdx - 1;
                    if (m[mStart] <= n[beforeMidIdx]) {
                        return n[beforeMidIdx];
                    } else if (m[mStart] > n[midIdx]) {
                        return n[midIdx];
                    } else {
                        return m[mStart];
                    }
                }

            } else {
                int midIdx = getMidIdx(nStart, nEnd);
                if ((m[mStart] > n[midIdx - 1] && m[mStart] <= n[midIdx]) || (m[mStart] >= n[midIdx] && m[mStart] < n[midIdx + 1])) {
                    return (double) (m[mStart] + n[midIdx]) / 2;
                } else {
                    if (m[mStart] < n[midIdx - 1]) {
                        return (double) (n[midIdx - 1] + n[midIdx]) / 2;
                    } else {
                        return (double) (n[midIdx] + n[midIdx + 1]) / 2;
                    }
                }

            }
        } else if (mLength == 2) {
            if (nLength == 2) {
                int max = Math.max(m[mEnd], n[nEnd]);
                int min = Math.min(m[mStart], n[nStart]);
                return (double) (m[mStart] + m[mEnd] + n[nStart] + n[nEnd] - max - min) / 2;
            } else if (nLength % 2 == 0) {
                int midIdx = getMidIdx(nStart, nEnd);
                int max = Math.max(m[mStart], n[midIdx - 2]);
                int min = Math.min(m[mEnd], n[midIdx + 1]);
                return compute4(n[midIdx], n[midIdx - 1], min, max) / 2;
            } else {
                int midIdx = getMidIdx(nStart, nEnd);
                int max = Math.max(m[mStart], n[midIdx - 1]);
                int min = Math.min(m[mEnd], n[midIdx + 1]);
                return compute3(n[midIdx], max, min);
            }
        }

        double mMedian = computeMedian(m, mStart, mEnd, mLength);
        double nMedian = computeMedian(n, nStart, nEnd, nLength);

        if (mMedian <= nMedian) {
            mStart = mLength % 2 == 0 && nLength % 2 == 0 ? getMidIdx(mStart, mEnd) - 1 : getMidIdx(mStart, mEnd);
            nEnd = getMidIdx(nStart, nEnd);
        } else {
            nStart = nLength % 2 == 0 ? getMidIdx(nStart, nEnd) - 1 : getMidIdx(nStart, nEnd);
            mEnd = getMidIdx(mStart, mEnd);
        }


        mLength = (mEnd - mStart) + 1;
        nLength = (nEnd - nStart) + 1;

        if (mLength > nLength) {
            int[] temp = m;
            m = n;
            n = temp;

            int tempStart = mStart;
            mStart = nStart;
            nStart = tempStart;

            int tempEnd = mEnd;
            mEnd = nEnd;
            nEnd = tempEnd;
        }

        return findMedian(m, n, mStart, mEnd, nStart, nEnd);

    }

    static double compute4(int a, int b, int c, int d) {
        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        int min = Math.min(a, Math.min(b, Math.min(c, d)));
        return (double) a + b + c + d - max - min;
    }

    static double compute3(int a, int b, int c) {
        return (a + b + c) - (Math.max(a, Math.max(b, c))) - (Math.min(a, Math.min(b, c)));
    }

    static int getMidIdx(int start, int end) {
        return (start + end + 1) / 2;
    }

    static double computeMedian(int[] arr, int start, int end, int length) {
        if (length == 2) {
            return (double) (arr[0] + arr[1]) / 2;
        }
        int mid = getMidIdx(start, end);
        if (length % 2 == 0) {
            return (double) (arr[mid] + arr[mid - 1]) / 2;
        } else {
            return arr[mid];
        }

    }
}
