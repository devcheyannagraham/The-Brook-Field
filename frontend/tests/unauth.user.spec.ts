import { test, expect } from '@playwright/test';

const username = `user_${Date.now()}@example.com`;


test("unauth user cannot access authorized routes", async ({ page }) => {
  await page.goto('https://localhost:4200/');
  await page.getByRole('link', { name: 'Register' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username);
  await page.locator('input[type="email"]').press('Tab');
  await page.getByRole('textbox').nth(1).fill('user');
  await page.getByRole('textbox').nth(1).press('Tab');
  await page.locator('form div').filter({ hasText: 'Confirm Password' }).getByRole('textbox').fill('user');
  await page.getByRole('main').getByRole('button', { name: 'Register' }).click();
  await expect(page.getByText(`Welcome, ${username.toUpperCase()}`)).toBeVisible();
  await page.goto('https://localhost:4200/publications');
  await expect(page.getByText('Unauthorized')).toBeVisible();;
  await page.goto('https://localhost:4200/publicationform');
  await expect(page.getByText('Unauthorized')).toBeVisible();;
  await page.goto('https://localhost:4200/admindashboard');
  await expect(page.getByText('Unauthorized')).toBeVisible();;
  await page.goto('https://localhost:4200/accessories');
  await expect(page.getByText('Unauthorized')).toBeVisible();;
  await page.goto('https://localhost:4200/accessoryform');
  await expect(page.getByText('Unauthorized')).toBeVisible();;
  await page.goto('https://localhost:4200/');
  await page.getByRole('link', { name: 'Login' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username);
  await page.locator('input[type="email"]').press('Tab');
  await page.locator('input[type="password"]').fill('user');
  await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
  await expect(page.getByText(`Welcome, ${username.toUpperCase()}`)).toBeVisible();
  await page.goto('https://localhost:4200/report/lowinventory');
  await expect(page.locator('#toaster')).toBeVisible();
  await page.getByText('Unauthorized').click();
});