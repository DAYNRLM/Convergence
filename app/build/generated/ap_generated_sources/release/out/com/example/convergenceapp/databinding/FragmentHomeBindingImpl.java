package com.example.convergenceapp.databinding;
import com.example.convergenceapp.R;
import com.example.convergenceapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.spinner_gp, 1);
        sViewsWithIds.put(R.id.village_vis, 2);
        sViewsWithIds.put(R.id.spinner_village, 3);
        sViewsWithIds.put(R.id.benvis, 4);
        sViewsWithIds.put(R.id.spinner_beneficiary, 5);
        sViewsWithIds.put(R.id.fathervis, 6);
        sViewsWithIds.put(R.id.father_name, 7);
        sViewsWithIds.put(R.id.mother_namevis, 8);
        sViewsWithIds.put(R.id.mother_name, 9);
        sViewsWithIds.put(R.id.ben_id_show_vis, 10);
        sViewsWithIds.put(R.id.benid_show, 11);
        sViewsWithIds.put(R.id.bank_details, 12);
        sViewsWithIds.put(R.id.othermember, 13);
        sViewsWithIds.put(R.id.llBank, 14);
        sViewsWithIds.put(R.id.mob_vis, 15);
        sViewsWithIds.put(R.id.edt_mobile, 16);
        sViewsWithIds.put(R.id.ben_avai_visi, 17);
        sViewsWithIds.put(R.id.spinner_ben_avail, 18);
        sViewsWithIds.put(R.id.any_family_visib, 19);
        sViewsWithIds.put(R.id.spinner_in_shg, 20);
        sViewsWithIds.put(R.id.lis_of_familyMember, 21);
        sViewsWithIds.put(R.id.Spinner_family, 22);
        sViewsWithIds.put(R.id.member_outerLay, 23);
        sViewsWithIds.put(R.id.member_nameET, 24);
        sViewsWithIds.put(R.id.ngp_visib, 25);
        sViewsWithIds.put(R.id.spinner_nrlm_gp, 26);
        sViewsWithIds.put(R.id.nrlm_vill_vissib, 27);
        sViewsWithIds.put(R.id.spinner_nrlm_village, 28);
        sViewsWithIds.put(R.id.shg_visi, 29);
        sViewsWithIds.put(R.id.spinner_shg_name, 30);
        sViewsWithIds.put(R.id.member_visi, 31);
        sViewsWithIds.put(R.id.spinner_member_name, 32);
        sViewsWithIds.put(R.id.willing_visib, 33);
        sViewsWithIds.put(R.id.spinner_willing, 34);
        sViewsWithIds.put(R.id.reason_visi, 35);
        sViewsWithIds.put(R.id.spinner_reason, 36);
        sViewsWithIds.put(R.id.btn_save, 37);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 38, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.AutoCompleteTextView) bindings[22]
            , (com.google.android.material.textfield.TextInputLayout) bindings[19]
            , (androidx.cardview.widget.CardView) bindings[12]
            , (com.google.android.material.textfield.TextInputLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (com.google.android.material.textfield.TextInputLayout) bindings[4]
            , (com.google.android.material.button.MaterialButton) bindings[37]
            , (android.widget.EditText) bindings[16]
            , (android.widget.TextView) bindings[7]
            , (android.widget.LinearLayout) bindings[6]
            , (com.google.android.material.textfield.TextInputLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[14]
            , (com.google.android.material.textfield.TextInputEditText) bindings[24]
            , (com.google.android.material.textfield.TextInputLayout) bindings[23]
            , (com.google.android.material.textfield.TextInputLayout) bindings[31]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.TextView) bindings[9]
            , (android.widget.LinearLayout) bindings[8]
            , (com.google.android.material.textfield.TextInputLayout) bindings[25]
            , (com.google.android.material.textfield.TextInputLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[13]
            , (com.google.android.material.textfield.TextInputLayout) bindings[35]
            , (com.google.android.material.textfield.TextInputLayout) bindings[29]
            , (android.widget.AutoCompleteTextView) bindings[18]
            , (android.widget.AutoCompleteTextView) bindings[5]
            , (android.widget.AutoCompleteTextView) bindings[1]
            , (android.widget.AutoCompleteTextView) bindings[20]
            , (android.widget.AutoCompleteTextView) bindings[32]
            , (android.widget.AutoCompleteTextView) bindings[26]
            , (android.widget.AutoCompleteTextView) bindings[28]
            , (android.widget.AutoCompleteTextView) bindings[36]
            , (android.widget.AutoCompleteTextView) bindings[30]
            , (android.widget.AutoCompleteTextView) bindings[3]
            , (android.widget.AutoCompleteTextView) bindings[34]
            , (com.google.android.material.textfield.TextInputLayout) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[33]
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