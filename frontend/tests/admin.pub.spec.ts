import { test, expect } from '@playwright/test';
import {url} from './port';

test("admin creates, reads, updates, deletes publications and publicationItems", async ({ page }) => {
  await page.goto('https://bfims-26f76b84e56f.herokuapp.com/');
  await expect(page.locator('h1')).toContainText('The Brook & Field');
  await expect(page.getByRole('button', { name: 'Login' })).toBeVisible();
  await page.getByRole('link', { name: 'Login' }).click();
  await expect(page.getByRole('heading', { name: 'Login' })).toBeVisible();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill('bfadmin');
  await page.locator('input[type="email"]').press('Tab');
  await page.locator('input[type="password"]').fill('bfadmin');
  await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
  await expect(page.getByText('Welcome, BFADMIN!')).toBeVisible();
  await page.locator("table").first().locator("tbody").locator("tr").first().locator("a").click();
  await expect(page.getByRole('heading', { name: 'Books' })).toBeVisible();

  await expect(page.getByRole('button', { name: 'Update Publication' })).toBeVisible();
  await page.getByRole('link', { name: 'Update Publication' }).click();
  await expect(page.getByRole('heading', { name: 'Update Publication' })).toBeVisible();
  await page.getByRole('textbox', { name: 'Title*' }).click();
  await page.getByRole('textbox', { name: 'Title*' }).fill('Wings of Myth: Fantasy Essaysplaywright');
  await expect(page.getByRole('button', { name: 'Submit' })).toBeVisible();
  await page.getByRole('button', { name: 'Submit' }).click();

  // await expect(page.getByText('Title: Wings of Myth: Fantasy')).toBeVisible();

  await expect(page.getByRole('button', { name: 'Update Publication' })).toBeVisible();
  await page.getByRole('link', { name: 'Update Publication' }).click();
  await page.getByRole('textbox', { name: 'Title*' }).click();
  await expect(page.getByRole('heading', { name: 'Update Publication' })).toBeVisible();
  await page.getByRole('textbox', { name: 'Title*' }).fill('Wings of Myth: Fantasy Essays');
  await page.getByRole('button', { name: 'Submit' }).click();

  await expect(page.getByRole('button', { name: 'New Publication' })).toBeVisible();
  await page.getByRole('link', { name: 'New Publication' }).click();
  await expect(page.getByRole('heading', { name: 'New Publication' })).toBeVisible();


  await page.getByRole('textbox', { name: 'Title*' }).click();
  await page.getByRole('textbox', { name: 'Title*' }).fill('Playwright');
  await page.getByLabel('Genre*').selectOption('COMEDY');
  await page.getByRole('spinbutton', { name: 'ISBN*' }).click();
  await page.getByRole('spinbutton', { name: 'ISBN*' }).fill('123456789');
  await page.getByRole('button', { name: 'Submit' }).click();
  await expect(page.getByText('ISBN\'s are 10 - 13 digits')).toBeVisible();
  await page.getByRole('spinbutton', { name: 'ISBN*' }).click();
  await page.getByRole('spinbutton', { name: 'ISBN*' }).fill('1234567890');
  await page.getByRole('textbox', { name: 'Publish Date*' }).fill('2025-10-18');
  await page.getByRole('textbox', { name: 'First Name*' }).click();
  await page.getByRole('textbox', { name: 'First Name*' }).fill('Play');
  await page.getByRole('textbox', { name: 'Last Name*' }).click();
  await page.getByRole('textbox', { name: 'Last Name*' }).fill('wright');
  await expect(page.getByRole('button', { name: 'Submit' })).toBeVisible();
  await page.getByRole('button', { name: 'Submit' }).click();


  await expect(page.getByRole('heading', { name: 'Playwright' })).toBeVisible();
  await expect(page.getByRole('button', { name: 'Add Items' })).toBeVisible();
  await page.getByRole('link', { name: 'Add Items' }).click();
  await page.getByLabel('Publication Item Type*').selectOption('JOURNAL');
  await page.getByRole('spinbutton', { name: 'Quantity*' }).click();
  await page.getByRole('spinbutton', { name: 'Quantity*' }).fill('3');
  await page.getByRole('textbox', { name: 'Edition*' }).click();
  await page.getByRole('textbox', { name: 'Edition*' }).fill('pwe');
  await page.getByLabel('Format*').selectOption('HARDCOPY');
  await page.getByRole('spinbutton', { name: 'Purchase Price*' }).click();
  await page.getByRole('spinbutton', { name: 'Purchase Price*' }).fill('8.36');
  await page.getByRole('spinbutton', { name: 'Rental Rate*' }).click();
  await page.getByRole('spinbutton', { name: 'Rental Rate*' }).fill('2.13');
  await page.getByRole('textbox', { name: 'Issue Date*' }).fill('2025-10-15');
  await page.getByRole('textbox', { name: 'Issue Number*' }).click();
  await page.getByRole('textbox', { name: 'Issue Number*' }).fill('123p');
  await page.getByRole('textbox', { name: 'Issue Name*' }).click();
  await page.getByRole('textbox', { name: 'Issue Name*' }).fill('tyu2');
  await page.getByRole('textbox', { name: 'Volume*' }).click();
  await page.getByRole('textbox', { name: 'Volume*' }).fill('vpe');
  await expect(page.getByRole('button', { name: 'Submit' })).toBeVisible();
  await page.getByRole('button', { name: 'Submit' }).click();
  await expect(page.getByText('Quantity:')).toBeVisible();
  await expect(page.getByRole('button', { name: 'delete' }).nth(1)).toBeVisible();
  await page.getByRole('button', { name: 'delete' }).nth(1).click();
  await expect(page.getByText('Quantity:')).toBeVisible();
  await expect(page.getByRole('button', { name: 'delete' }).nth(1)).toBeVisible();
  await page.getByRole('button', { name: 'delete' }).nth(1).click();
  await expect(page.getByText('Quantity:')).toBeVisible();
  await expect(page.getByRole('button', { name: 'delete', exact: true })).toBeVisible();
  await page.getByRole('button', { name: 'delete', exact: true }).click();
  await expect(page.getByText('Quantity:')).toBeVisible();
  await expect(page.getByText('No Books')).toBeVisible();
  await expect(page.getByRole('button', { name: 'Delete Publication' })).toBeVisible();
  await page.getByRole('button', { name: 'Delete Publication' }).click();
  await page.getByRole('button', { name: 'back' }).click();
  await expect(page.getByText('Welcome, BFADMIN!')).toBeVisible();
  await expect(page.getByRole('button', { name: 'Logout' })).toBeVisible();
  await page.getByRole('button', { name: 'Logout' }).click();
  await expect(page.getByRole('heading', { name: 'Login' })).toBeVisible();
});
