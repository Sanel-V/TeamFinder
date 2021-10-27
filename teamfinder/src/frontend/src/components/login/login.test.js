import { render, screen } from '@testing-library/react';
import { Login } from './login';

test('renders learn react link', () => {
  render(<Login />);
  const linkElement = screen.getByText(/Username/i);
  expect(linkElement).toBeInTheDocument();
});
