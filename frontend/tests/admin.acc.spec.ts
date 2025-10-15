import { test, expect } from '@playwright/test';
import {url} from './port';

test("admin creates, reads, updates, deletes accesssories and accessory items", async ({ page }) => {
  await page.goto(url);

  //login
  await page.getByRole('button', { name: 'Login' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill('bfadmin@mail.com');
  await page.locator('input[type="email"]').press('Tab');
  await page.locator('input[type="password"]').fill('bfadministrator');
  await page.getByRole('main').getByRole('button', { name: 'Login' }).click();

  //view and update accessory
  await page.getByRole('link', { name: 'Admin Dashboard' }).click();
  await page.getByRole('cell', { name: 'Classic Bookmark' }).click();
  await page.getByRole('link', { name: 'Classic Bookmark' }).click();
  await page.getByRole('link', { name: 'Update Accessory' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name*' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name*' }).fill('Classic Bookmark1');
  await page.getByRole('button', { name: 'Update Accessory' }).click();
  await expect(page.getByText('Accessory Name: Classic')).toBeVisible();

  // create accessory
  await page.getByRole('link', { name: 'New Accessory' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name*' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name*' }).fill('PlayWright');
  await page.getByLabel('Accessory Type*').selectOption('BOOKMARK');
  await page.getByRole('textbox', { name: 'Price*' }).click();
  await page.getByRole('textbox', { name: 'Price*' }).fill('2.56');
  await page.getByRole('textbox', { name: 'Quantity*' }).click();
  await page.getByRole('textbox', { name: 'Quantity*' }).fill('2');
  await page.getByRole('button', { name: 'Add Accessory' }).click();
  await expect(page.getByText('Accessory Name: PlayWright')).toBeVisible();

  // delete accessory items
  await page.getByRole('row', { name: 'AVAILABLE delete' }).first().getByRole('button').click();
  await expect(page.getByRole('list')).toContainText('Quantity: 1');
  await page.getByRole('row', { name: 'AVAILABLE delete' }).first().getByRole('button').click();
  await expect(page.getByRole('list')).toContainText('Quantity: 0');

  //delete accessory
  await page.getByRole('button', { name: 'Delete Accessory' }).first().click();
  await page.getByRole('button', { name: 'Logout' }).click();
});
