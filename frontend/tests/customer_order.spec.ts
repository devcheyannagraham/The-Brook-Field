import { test, expect } from '@playwright/test';

//run once after db starts, subsequent runs will fail due to item already purchased

test('test', async ({ page }) => {
  await page.goto('https://localhost:4200/');
  await page.getByRole('button', { name: 'Shop' }).click();
  await page.getByRole('link', { name: 'Laugh Lines: A Satirical' }).click();
  await page.getByRole('row', { name: 'BOOK 1th Edition HARDCOPY $15' }).getByRole('button').first().click();
  await page.getByRole('button', { name: 'Shop' }).click();
  await page.getByRole('link', { name: 'Insulated Mug' }).click();
  await page.getByRole('button', { name: 'Purchase' }).click();
  await page.getByRole('link', { name: 'Checkout' }).click();
  await page.getByRole('textbox', { name: 'First Name*' }).click();
  await page.getByRole('textbox', { name: 'First Name*' }).fill('Playwright');
  await page.getByRole('textbox', { name: 'Last Name*' }).click();
  await page.getByRole('textbox', { name: 'Last Name*' }).fill('playwright');
  await page.getByRole('textbox', { name: 'Street address*' }).click();
  await page.getByRole('textbox', { name: 'Street address*' }).fill('123 play ave');
  await page.getByRole('textbox', { name: 'Street address*' }).press('Tab');
  await page.getByRole('textbox', { name: 'City*' }).fill(' wright');
  await page.getByRole('textbox', { name: 'City*' }).press('Tab');
  await page.getByRole('textbox', { name: 'State*' }).fill('USA');
  await page.getByRole('textbox', { name: 'ZipCode*' }).click();
  await page.getByRole('textbox', { name: 'ZipCode*' }).fill('12345e');
  await page.getByRole('textbox', { name: 'Country*' }).click();
  await page.getByRole('textbox', { name: 'Country*' }).fill('USA');
  await page.getByRole('textbox', { name: 'Email*' }).click();
  await page.getByRole('textbox', { name: 'Email*' }).fill('play@wright.com');
  await page.getByRole('textbox', { name: 'Email*' }).press('Tab');
  await page.getByRole('textbox', { name: 'Phone*' }).fill('123-456-7890');
  await page.getByRole('button', { name: 'Submit Order' }).click();
  await expect(page.getByText('CartTotal Items:0Total Price')).toBeVisible();
});