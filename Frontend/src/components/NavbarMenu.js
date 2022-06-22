import React, { useState } from 'react'
import { PersonOutlineRounded, Logout } from '@mui/icons-material'
import { IconButton, Avatar, Menu, MenuItem, Divider, Tooltip, ListItemIcon } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useSnackbar } from 'notistack';

const NavbarMenu = ({ user }) => {
    const navigate = useNavigate();
    const { enqueueSnackbar } = useSnackbar();

    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const logout = async () => {
        await localStorage.removeItem("token");
        await localStorage.removeItem("user");
        window.location.reload();
        enqueueSnackbar('Oturumun sonlandı!', { variant: 'info' });
    }


    return (
        <div className="nav-buttons">
            {
                user?.role === "ROLE_CUSTOMER" &&
                <button className="navButton" onClick={() => navigate('/')}>Keşfet</button>
            }
            <Tooltip title="Hesabım">
                <IconButton
                    onClick={handleClick}
                    size="small"
                    aria-controls={open ? 'account-menu' : undefined}
                    aria-haspopup="true"
                    aria-expanded={open ? 'true' : undefined}
                >
                    <Avatar sx={{ width: 30, height: 30 }} />
                </IconButton>
            </Tooltip>
            <Menu
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                onClick={handleClose}
                PaperProps={{
                    elevation: 0,
                    sx: {
                        overflow: 'visible',
                        filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                        mt: 1.5,
                        '& .MuiAvatar-root': {
                            width: 32,
                            height: 32,
                            ml: -0.5,
                            mr: 1,
                        },
                        '&:before': {
                            content: '""',
                            display: 'block',
                            position: 'absolute',
                            top: 0,
                            right: 14,
                            width: 10,
                            height: 10,
                            bgcolor: 'background.paper',
                            transform: 'translateY(-50%) rotate(45deg)',
                            zIndex: 0,
                        },
                    },
                }}
                transformOrigin={{ horizontal: 'right', vertical: 'top' }}
                anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
            >
                <MenuItem disabled>
                    {
                        user?.role === "ROLE_CUSTOMER" ? user?.name :
                            user?.role === "ROLE_SELLER" ? user?.storeName :
                                "Kullanıcı"
                    }
                </MenuItem>
                <Divider />
                {
                    user?.role === "ROLE_SELLER" ?
                        <MenuItem onClick={() => navigate(`/store?id=${user?.id}`)}>
                            <ListItemIcon>
                                <PersonOutlineRounded fontSize="small" />
                            </ListItemIcon>
                            Profilim
                        </MenuItem>
                        : <MenuItem onClick={() => navigate(`/dashboard`)}>
                            <ListItemIcon>
                                <PersonOutlineRounded fontSize="small" />
                            </ListItemIcon>
                            Bana Özel
                        </MenuItem>
                }
                <MenuItem onClick={() => logout()}>
                    <ListItemIcon>
                        <Logout fontSize="small" />
                    </ListItemIcon>
                    Çıkış yap
                </MenuItem>
            </Menu>
        </div>
    );
}

export default NavbarMenu;