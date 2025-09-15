import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('http://localhost:4200/accessoryform');
  await page.getByText('New AccessoryAccessory Name').click();
  await page.getByRole('textbox', { name: 'Accessory Name' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name' }).fill('PW');
  await page.getByRole('textbox', { name: 'Accessory Name' }).press('Tab');
  await page.getByLabel('Accessory Type BookmarkMugPen').press('ArrowDown');
  await page.getByLabel('Accessory Type BookmarkMugPen').selectOption('BOOKMARK');
  await page.getByLabel('Accessory Type BookmarkMugPen').press('Tab');
  await page.getByRole('textbox', { name: 'Price' }).fill('2');
  await page.getByRole('textbox', { name: 'Price' }).press('Tab');
  await page.getByRole('textbox', { name: 'Quantity' }).fill('3');
  await page.getByRole('button', { name: 'Add Accessory' }).click();
  await expect(page.getByRole('list')).toContainText('Accessory Name: PW');
  await expect(page.getByRole('list')).toContainText('Accessory Type: BOOKMARK');
  await expect(page.getByRole('list')).toContainText('Price: 2');
  await expect(page.getByRole('list')).toContainText('Quantity: 3');
  await expect(page.locator('tbody')).toContainText('AVAILABLE');
  await page.getByRole('link', { name: 'Update Accessory' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name' }).click();
  await page.getByRole('textbox', { name: 'Accessory Name' }).fill('PW updated');
  await page.getByRole('button', { name: 'Update Accessory' }).click();
  await expect(page.locator('h3')).toContainText('PW updated');
  await page.getByRole('link', { name: 'Back' }).click();
  await expect(page.locator('tbody')).toContainText('PW updated');
});