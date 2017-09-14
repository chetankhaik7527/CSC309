## Assignment 3
Due 9/19

Revise assignment 1 so that there is a settings activity where the user can change the tax rates, and the income levels at which new level is active.  It should also display an error message and reject any settings that don't make sense (an example of this would be having both of the first two tax rates end at the same income, other meaningless combinations also exist).

Calculate the taxes as follows:

* If the income falls into the first tax bracket , tax due = rate1 * income
* If the income falls into the second tax bracket, tax due = rate1 * limit1 + rate2 * (income - limit1)
* If the income falls into the top tax bracket, tax due = rate1 * limit1 + rate2 * (limit2 - limit1) + rate3 * (income - limit2)

**NOTE**: Your app (and all future apps) must preserve information over an orientation change.
