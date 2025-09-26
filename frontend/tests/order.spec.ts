import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
    await page.goto('http://localhost:4200/');
    await page.getByRole('button', { name: 'Login' }).click();
    await page.locator('input[type="email"]').click();
    await page.locator('input[type="email"]').fill('admin');
    await page.locator('input[type="email"]').press('Tab');
    await page.locator('input[type="password"]').fill('admin');
    await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
    await expect(page.getByText('BFIMSbackLogout')).toBeVisible();
    await page.goto('http://localhost:4200/shop');
    await expect(page.getByText('BFIMSbackLogin Register')).toBeVisible();
    await page.getByRole('link', { name: 'The Great Gatsby' }).click();
    await page.getByRole('button', { name: 'Rent' }).first().click();
    await expect(page.getByRole('button', { name: 'Remove from Cart' })).toBeVisible();
    await page.getByRole('button', { name: 'Checkout' }).click();
    await page.getByRole('textbox', { name: 'Email' }).click();
    await page.getByRole('textbox', { name: 'Email' }).click();
    await page.getByRole('textbox', { name: 'Email' }).fill('admin');
    await page.getByRole('button', { name: 'Submit Order' }).click();
    await expect(page.locator('h2')).toContainText('Our Shop');
    await page.getByRole('link', { name: 'The Great Gatsby' }).click();
    await expect(page.locator('shop-item-detail')).toContainText('Quantity: 1');
    await page.goto('http://localhost:4200/');
    await page.getByRole('button', { name: 'Login' }).click();
    await page.locator('input[type="email"]').click();
    await page.locator('input[type="email"]').fill('admin');
    await page.locator('input[type="email"]').press('Tab');
    await page.locator('input[type="password"]').fill('admin');
    await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
    await expect(page.getByText('BFIMSbackLogout')).toBeVisible();
    await page.goto('http://localhost:4200/shop');
    await expect(page.getByText('BFIMSbackLogin Register')).toBeVisible();
    await page.getByRole('link', { name: 'The Great Gatsby' }).click();
    await page.getByRole('button', { name: 'Rent' }).first().click();
    await expect(page.getByRole('button', { name: 'Remove from Cart' })).toBeVisible();
    await page.getByRole('button', { name: 'Checkout' }).click();
    await page.getByRole('textbox', { name: 'Email' }).click();
    await page.getByRole('textbox', { name: 'Email' }).click();
    await page.getByRole('textbox', { name: 'Email' }).fill('alex.smith@email.com');
    await page.getByRole('button', { name: 'Submit Order' }).click();
    await expect(page.locator('h2')).toContainText('Our Shop');
    await page.getByRole('link', { name: 'The Great Gatsby' }).click();
    await expect(page.locator('shop-item-detail')).toContainText('Quantity: Out of Stock');


});