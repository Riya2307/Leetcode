class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null ) return list2;
        else if (list2 == null) return list1;

        ListNode l1 = list1,
            l2 = list2,
            l3 = new ListNode(0),
            pHead = l3;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }

        l3.next = l1 == null ? l2 : l1;        
        return pHead.next;
    }
}
 