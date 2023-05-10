package com.example.convergenceapp.databinding;
import com.example.convergenceapp.R;
import com.example.convergenceapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMemberBindingImpl extends FragmentMemberBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ngp_visib, 1);
        sViewsWithIds.put(R.id.spinner_nrlm_gp, 2);
        sViewsWithIds.put(R.id.nrlm_vill_vissib, 3);
        sViewsWithIds.put(R.id.spinner_nrlm_village, 4);
        sViewsWithIds.put(R.id.shg_visi, 5);
        sViewsWithIds.put(R.id.spinner_shg_name, 6);
        sViewsWithIds.put(R.id.member_visi, 7);
        sViewsWithIds.put(R.id.spinner_member_name, 8);
        sViewsWithIds.put(R.id.llSpouseName, 9);
        sViewsWithIds.put(R.id.tvSpouseName, 10);
        sViewsWithIds.put(R.id.tvPartOfShg, 11);
        sViewsWithIds.put(R.id.rgPartOfShg, 12);
        sViewsWithIds.put(R.id.rbPartOfShgYes, 13);
        sViewsWithIds.put(R.id.rbPartOfShgNo, 14);
        sViewsWithIds.put(R.id.llMobile, 15);
        sViewsWithIds.put(R.id.etMobile, 16);
        sViewsWithIds.put(R.id.llMobileBelongsTo, 17);
        sViewsWithIds.put(R.id.spinnerMobileBelongsTo, 18);
        sViewsWithIds.put(R.id.bankShowVisid, 19);
        sViewsWithIds.put(R.id.txtBankNameShowid, 20);
        sViewsWithIds.put(R.id.branchShowVisid, 21);
        sViewsWithIds.put(R.id.txtbranchNameShowid, 22);
        sViewsWithIds.put(R.id.llReason, 23);
        sViewsWithIds.put(R.id.spinnerDisContinueReason, 24);
        sViewsWithIds.put(R.id.llBank, 25);
        sViewsWithIds.put(R.id.spinnerBank, 26);
        sViewsWithIds.put(R.id.llBranch, 27);
        sViewsWithIds.put(R.id.spinnerBranch, 28);
        sViewsWithIds.put(R.id.llIfscCode, 29);
        sViewsWithIds.put(R.id.tvIfscCode, 30);
        sViewsWithIds.put(R.id.llAccountNumber, 31);
        sViewsWithIds.put(R.id.etAccountNumber, 32);
        sViewsWithIds.put(R.id.btn_save, 33);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMemberBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 34, sIncludes, sViewsWithIds));
    }
    private FragmentMemberBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[21]
            , (com.google.android.material.button.MaterialButton) bindings[33]
            , (com.google.android.material.textfield.TextInputEditText) bindings[32]
            , (com.google.android.material.textfield.TextInputEditText) bindings[16]
            , (com.google.android.material.textfield.TextInputLayout) bindings[31]
            , (com.google.android.material.textfield.TextInputLayout) bindings[25]
            , (com.google.android.material.textfield.TextInputLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[29]
            , (com.google.android.material.textfield.TextInputLayout) bindings[15]
            , (com.google.android.material.textfield.TextInputLayout) bindings[17]
            , (com.google.android.material.textfield.TextInputLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[9]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (com.google.android.material.textfield.TextInputLayout) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[3]
            , (android.widget.RadioButton) bindings[14]
            , (android.widget.RadioButton) bindings[13]
            , (android.widget.RadioGroup) bindings[12]
            , (com.google.android.material.textfield.TextInputLayout) bindings[5]
            , (android.widget.AutoCompleteTextView) bindings[26]
            , (android.widget.AutoCompleteTextView) bindings[28]
            , (android.widget.AutoCompleteTextView) bindings[24]
            , (android.widget.AutoCompleteTextView) bindings[8]
            , (android.widget.AutoCompleteTextView) bindings[18]
            , (android.widget.AutoCompleteTextView) bindings[2]
            , (android.widget.AutoCompleteTextView) bindings[4]
            , (android.widget.AutoCompleteTextView) bindings[6]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[22]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}