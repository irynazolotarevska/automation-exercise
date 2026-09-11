package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.regex.Pattern;

public class AccountCreatedDeleted {
    private Page page;

    private Locator header;
    private Locator continueButton;

    public AccountCreatedDeleted(Page page){
        this.page = page;
        header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(Pattern.compile("Account (Created|Deleted)!")));
        continueButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue"));
    }

    public boolean isHeaderVisible() {
        header.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return header.isVisible();
    }
    public void clickContinue(){
        continueButton.click();
    }
}
