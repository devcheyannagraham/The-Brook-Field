import { test, expect } from '@playwright/test';
import {url} from './port';

const username = `user_${Date.now()}@example.com`;

test('unique user can register', async ({ page }) => {
  //register unique user
  await page.goto(url);
  await page.getByText('backShopLogin Register').click();
  await page.getByRole('link', { name: 'Register' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username);
  await page.getByRole('textbox').nth(1).click();
  await page.getByRole('textbox').nth(1).fill('playwrighttest');
  await page.getByRole('textbox').nth(1).press('Tab');
  await page.locator('form div').filter({ hasText: 'Confirm Password' }).getByRole('textbox').fill('playwrighttest');
  await page.getByRole('main').getByRole('button', { name: 'Register' }).click();

  //login w/ unregister user
  await page.getByRole('button', { name: 'Logout' }).click();
  await page.locator('input[type="email"]').click();
  await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username + '2');
  await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
  await page.locator('input[type="password"]').click();
  await page.locator('input[type="password"]').fill('playwrighttest');
  await page.getByRole('main').getByRole('button', { name: 'Login' }).click();
  await expect(page.getByText('User does not exist')).toBeVisible();

  //register w/ same user
  await page.getByRole('link', { name: 'Register' }).click();
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username);

    // no password
    await page.getByRole('main').getByRole('button', { name: 'Register' }).click();
    await page.locator('form div').filter({ hasText: 'PasswordPassword is required.' }).getByRole('textbox').click();
    await page.locator('form div').filter({ hasText: 'PasswordPassword is required.' }).getByRole('textbox').fill('playwrighttest');

    // no confirm password
    await page.getByRole('main').getByRole('button', { name: 'Register' }).click();
    await page.locator('form div').filter({ hasText: 'Please confirm your password' }).getByRole('textbox').click();
    await page.locator('form div').filter({ hasText: 'Please confirm your password' }).getByRole('textbox').fill('playwrighttest');
    await page.getByRole('main').getByRole('button', { name: 'Register' }).click();
    await expect(page.getByText('Username Unavailable')).toBeVisible();

    // register w/ unique user
  await page.locator('input[type="email"]').click();
  await page.locator('input[type="email"]').fill(username + '2');
  await page.getByRole('main').getByRole('button', { name: 'Register' }).click();
});
